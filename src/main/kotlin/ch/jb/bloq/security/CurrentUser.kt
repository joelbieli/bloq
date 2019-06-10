package ch.jb.bloq.security

import org.springframework.security.core.annotation.AuthenticationPrincipal
import kotlin.annotation.MustBeDocumented

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
@Retention
@MustBeDocumented
@AuthenticationPrincipal
annotation class CurrentUser