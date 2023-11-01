package edu.whu.demo.aspect;

import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.jws.Oneway;
import java.util.*;

@Aspect
@Component
public class ApiAspect {
    final Map<String,ApiInfo> apiInfoMap = Collections.synchronizedMap(new HashMap<>());

    @Pointcut("execution(* edu.whu.demo.controller.*.*(..))")
    public void apiPointCut(){
    }
    @Around("apiPointCut()")
    public Object ApiCallingInfo(ProceedingJoinPoint jp) throws Throwable {
        String methodSig = jp.getKind().toString();
        ApiInfo apiInfo = apiInfoMap.containsKey(methodSig)? apiInfoMap.get(methodSig): new ApiInfo();
        int callNum = apiInfo.getCallNum();
        callNum++;
        long t1 = Calendar.getInstance().getTimeInMillis();
        Object retValue = jp.proceed();
        long t2 = Calendar.getInstance().getTimeInMillis();
        List<Long> time = apiInfo.getTime();
        time.add(t2-t1);
        apiInfo.setCallNum(callNum);
        apiInfo.setTime(time);
        apiInfoMap.put(methodSig,apiInfo);
        return retValue;
    }
    @AfterThrowing("apiPointCut()")
    public void apiThrowingInfo(JoinPoint jp){
        String methodSig = jp.getKind().toString();
        ApiInfo apiInfo = apiInfoMap.containsKey(methodSig)? apiInfoMap.get(methodSig): new ApiInfo();
        int expNum = apiInfo.getExpNum();
        expNum++;
        apiInfo.setExpNum(expNum);
    }
    public void showResult(){
        for(String methodSig:apiInfoMap.keySet()){
            ApiInfo v=apiInfoMap.get(methodSig);
            int callNum = v.getCallNum();
            List<Long> time = v.getTime();
            Long maxTime = Collections.max(time);
            Long minTime = Collections.min(time);
            Long sum=0L;
            for(Long t:time){
                sum+=t;
            }
            Long averageTime = sum/ time.size();
            int expTime = v.getExpNum();
            String formatted = String.format("callNum: %d, maxTime: %s, minTime: %s, averageTime: %s,expNum: %d", callNum, maxTime,minTime,averageTime,expTime);
            System.out.println(methodSig+formatted);
        }
    }
}
@Data
class ApiInfo{
    int callNum;
    List<Long> time = new ArrayList<>();
    int expNum;
}
