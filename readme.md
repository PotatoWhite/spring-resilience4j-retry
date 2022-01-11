# Resilience4j - Retry

## 실패 -> 재시도
- Exception 발생시 재시도 함
- builkhead가 Runtime상의 동시성의 제한을 한다면, ratelimiter는 단위 시간당의 호출량을 제한할 뿐 동시성을 제한하지는 않는다.


## 설정
```yaml
resilience4j:
  retry:
    instances:
      sampleRetry:
# 재시도 횟수
        maxAttempts: 3
# 재시도시 대기시간
        waitDuration: 5s
# 재시도 대기시간 증가 사용        
        enableExponentialBackoff: true
# 재시도 대기시간 증가 곱하기 waitDuration이 5초인경우 5->10->20 이렇게 천천히 증가
        exponentialBackoffMultiplier: 2

```
