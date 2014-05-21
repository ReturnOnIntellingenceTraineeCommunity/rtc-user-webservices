package net.github.rtc.micro.user.annotations;

/**
 * Created by ivan on 21.05.14.
 * Annotation to detect what fields will be displayed for example in exel report
 */
@java.lang.annotation.Target(java.lang.annotation.ElementType.FIELD)
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface ForReport {
}
