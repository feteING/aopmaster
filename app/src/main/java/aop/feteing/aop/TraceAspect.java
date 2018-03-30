package aop.feteing.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * test1 顺序执行第一个执行接收发来的消息
 *
 * @Around("pointCut()") public Object weaveJoinPoint2(ProceedingJoinPoint joinPoint) throws Throwable {
 * Log.e("====< test", "");
 * return "";
 * }
 * <p>
 * 方法执行效率，与出入参数
 */
//@Aspect定义切面
@Aspect
public class TraceAspect {

    //@Pointcut定义切入点
    //execution 定义切面上需要执行的连接点   * *(..)代表 任意类的任意方法。
    //执行任意类的任意方法注解了cn.lee.aop.annotation.Trace的连接点
    @Pointcut("execution(@aop.feteing.aop.Trace  * *(..))")
    public void pointCut() {
    }


    //定义Advice，织入代码。
    @Around("pointCut()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取功能名称
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Trace anno = sign.getMethod().getAnnotation(Trace.class);
        String func = anno.value();

        Object[] args = joinPoint.getArgs();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            if (i == args.length - 1) {
                sb.append(args[i].toString());
            } else {
                sb.append(args[i].toString() + "_");
            }
        }

        Log.e("====< 入参:", sb.toString());

        long start = System.currentTimeMillis();
        //执行，功能方法

        Object ret = joinPoint.proceed(args);
        if (ret != null) {
            Log.e("====< 出参:", ret.toString());
        }


        long duration = System.currentTimeMillis() - start;
        Log.e("====< trace", String.format("功能：%s，耗时：%d ms", func, duration));


        return ret;
    }


}