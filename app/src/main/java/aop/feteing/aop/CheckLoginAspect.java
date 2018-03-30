package aop.feteing.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CheckLoginAspect {

    @Pointcut("execution(@aop.feteing.aop.CheckLogin * *(..))")//方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取user user为null跳转到login界面去登陆
    /*    User user;
        if (null == user) {
          //goto loginActivity
            return;
        }*/
        Log.e("====<","login begin");
        joinPoint.proceed();//执行原方法
    }
}