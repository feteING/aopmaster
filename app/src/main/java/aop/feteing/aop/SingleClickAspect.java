package aop.feteing.aop;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;
import java.util.LinkedList;

import aop.feteing.util.ClickViewModel;
import aop.feteing.util.LimitQueue;

@Aspect
public class SingleClickAspect {
    LimitQueue queue = new LimitQueue(10);
    public static final int MIN_CLICK_DELAY_TIME = 600;//间隔时间600ms

    @Pointcut("execution(@aop.feteing.aop.SingleClick * *(..))")//根据SingleClick注解找到方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")
    public Object aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        View view = null;
        for (Object arg : joinPoint.getArgs())
            if (arg instanceof View) view = (View) arg;
        if (view != null) {
            //队列中能否获取view 不能获取自己执行方法，可以获取判断时间通过时间决定是否执行
            ClickViewModel cvm = getLastTime(view);
            if (cvm == null) {
                long currentTime = Calendar.getInstance().getTimeInMillis();
                ClickViewModel clickViewModel = new ClickViewModel(currentTime, view.getId());
                queue.offer(clickViewModel);
                Log.e("====< testClick ", "ClickViewModel is null and proceed");
                result = joinPoint.proceed();//执行原方法
            } else {
                long lastClickTime = cvm.getLastClickTime();
                long currentTime = Calendar.getInstance().getTimeInMillis();
                cvm.setLastClickTime(currentTime);
                queue.update(cvm.getCurIndex(), cvm);

                if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {//过滤掉600毫秒内的连续点击
                    Log.e("====< testClick ", "ClickViewModel is not null and proceed");
                    result = joinPoint.proceed();//执行原方法
                }
            }


        } else {
            Log.e("====< testClick ", "view is null");
        }
        return result;
    }

    private ClickViewModel getLastTime(View view) {
        try {
            int viewId = view.getId();
            LinkedList<ClickViewModel> lists = queue.getLists();
            if (lists != null && lists.size() > 0) {
                for (int i = 0; i < lists.size(); i++) {
                    ClickViewModel clickViewModel = lists.get(i);
                    clickViewModel.setCurIndex(i);
                    if (viewId == clickViewModel.getViewId()) {
                        Log.e("====< clickviewmodel:", clickViewModel.toString());
                        return clickViewModel;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
