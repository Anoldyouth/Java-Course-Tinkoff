package edu.hw2.Task4;

public class CallStackHelper {
    private CallStackHelper() {
    }

    /**
     * Функция для получения информации о вызванном методе
     *
     * @return имена класса и метода
     */
    public static CallingInfo callingInfo() {
        try {
            throw new Exception();
        } catch (Exception e) {
            StackTraceElement stackTraceElement = e.getStackTrace()[1];
            return new CallingInfo(stackTraceElement.getClassName(), stackTraceElement.getMethodName());
        }
    }
}
