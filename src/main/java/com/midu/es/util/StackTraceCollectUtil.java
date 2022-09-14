package com.midu.es.util;


import com.midu.es.annotation.NotNull;

/**
 * 异常堆栈详细信息收集
 * 例如:
 * 用于收集 NullPointerException 的详细堆栈信息
 * <p>
 * lineNumber == -2  is a native method
 *
 * @author zhangqy
 * @version 1.0.0
 * @createTime 2022年09月07日 15:05:00
 */
public final class StackTraceCollectUtil {


    private static final String CAUSED_BY = "\r\nCaused by:";
    private static final String SUPPRESSED = "\r\n\tSuppressed:";

    /**
     * 异常堆栈信息收集
     * <p>
     * ExceptionClassName:e.Message
     * at ClassName#MethodName(LineNumber)
     * at ClassName#MethodName(LineNumber)
     * ...
     *
     * @param t Throwable
     * @return 堆栈信息 string
     */
    public static
    @NotNull
    String collectStackTrace(@NotNull final Throwable t) {

        return collectStackTrace(t, -1);
    }

    /**
     * 异常堆栈信息收集 , 可限制收集行数 , 减少信息打印
     * <p>
     * ExceptionClassName:e.Message
     * at ClassName#MethodName(LineNumber)
     * at ClassName#MethodName(LineNumber)
     * ...
     *
     * @param t                   throwable
     * @param collectLargestLines 默认收集 collectLargestLines 行 , 如果 collectLargestLines < 0 默认输出全部
     * @return 堆栈信息 string
     */
    public static
    @NotNull
    String collectStackTrace(@NotNull final Throwable t, final int collectLargestLines) {

        //默认初始为大小 7000
        final StringBuilder sb = new StringBuilder(7000);
        collectStackTrace(sb, t, collectLargestLines);

        return sb.toString();
    }

    /**
     * 异常堆栈信息收集
     * <p>
     * ExceptionClassName:e.Message
     * at ClassName#MethodName(LineNumber)
     * at ClassName#MethodName(LineNumber)
     * ...
     *
     * @param sb 在 sb 基础上 append 异常堆栈信息
     * @param t  throwable
     * @return 堆栈信息
     */
    private static
    @NotNull
    String collectStackTrace(@NotNull final StringBuilder sb, @NotNull final Throwable t) {

        return collectStackTrace(sb, t, -1);
    }

    /**
     * 异常堆栈信息收集 , 可限制收集行数 , 减少信息打印
     * <p>
     * ExceptionClassName:e.Message
     * at ClassName#MethodName(LineNumber)
     * at ClassName#MethodName(LineNumber)
     * ...
     *
     * @param sb                  在 sb 基础上 append 异常堆栈信息
     * @param t                   throwable
     * @param collectLargestLines 默认收集 collectLargestLines 行 , 如果 collectLargestLines < 0 默认输出全部
     * @return 堆栈信息
     */
    private static
    @NotNull
    String collectStackTrace(@NotNull final StringBuilder sb, @NotNull final Throwable t, final int collectLargestLines) {

        getStackTrace(sb, t, collectLargestLines);

        final Throwable[] suppressed = t.getSuppressed();
        if (suppressed != null) {
            for (Throwable t1 : suppressed) {
                sb.append(SUPPRESSED);
                collectStackTrace(sb, t1);
            }
        }

        final Throwable cause = t.getCause();
        if (cause != null) {
            sb.append(CAUSED_BY);
            collectStackTrace(sb, cause);
        }

        return sb.toString();
    }

    /**
     * 拼接 Throwable 的堆栈信息
     *
     * @param sb                  在 sb 基础上 append 异常堆栈信息
     * @param t                   throwable
     * @param collectLargestLines 默认收集 collectLargestLines 行 , 如果 collectLargestLines < 0 默认输出全部
     * @return StringBuilder
     */
    private static
    @NotNull
    StringBuilder getStackTrace(@NotNull final StringBuilder sb, @NotNull final Throwable t, int collectLargestLines) {

        final String name = t.getClass().getName();
        final String message = t.getMessage();
        sb.append(
                (t instanceof NullPointerException) ?
                        (message == null ? name : name + ":" + message) :
                        name + ":" + message
        );


        final StackTraceElement[] stackElements = t.getStackTrace();
        if (stackElements != null) {
            collectLargestLines = collectLargestLines < 0 ? stackElements.length : collectLargestLines;
            for (int i = 0; i < collectLargestLines; i++) {
                StackTraceElement ste = stackElements[i];
                sb
                        .append("\n\tat ")
                        .append(ste.getClassName())
                        .append(".")
                        .append(ste.getMethodName())
                        .append("(")
                        .append(ste.getLineNumber())
                        .append(")");
            }
        }

        return sb;
    }

}
