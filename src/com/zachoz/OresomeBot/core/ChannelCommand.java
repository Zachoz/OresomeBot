package com.zachoz.OresomeBot.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ChannelCommand {

    public String name() default "";

    public String description() default "";

    public String usage() default "";

    public PermissionLevel permissionLevel() default PermissionLevel.REGULAR;

    int minArgs() default 0;

    int maxArgs() default Integer.MAX_VALUE;

    public boolean privateMessage() default false;

}
