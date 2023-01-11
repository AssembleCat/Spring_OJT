package application.config.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.aop.framework.Advised
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime
import java.time.Period
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Component
@Aspect
class DatabaseTimestampAspect {
    // Logger
    private val log = org.slf4j.LoggerFactory.getLogger(DatabaseTimestampAspect::class.java)

    // Marks whole method that ends with Repository bean
    @Around("execution(* org.springframework.data.repository.CrudRepository+.*(..))")
    fun checkDatabaseExectime(joinPoint: ProceedingJoinPoint): Any? {
        val start = LocalDateTime.now()
            val result = joinPoint.proceed()
        val end = LocalDateTime.now()
        val executionTime: Duration = Duration.between(start, end)
        log.info("${joinPoint.`this`.javaClass.genericInterfaces[0].typeName}.${joinPoint.signature.name}()" +
                "'s execution time = ${executionTime.toMillis()} ms")
        return result
    }
}