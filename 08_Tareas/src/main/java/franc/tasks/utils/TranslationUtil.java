package franc.tasks.utils;

import franc.tasks.models.Priority;
import franc.tasks.models.Status;

import java.util.HashMap;
import java.util.Map;

public class TranslationUtil {
    private static final Map<Priority, String> priorityTranslations = new HashMap<>();
    private static final Map<Status, String> statusTranslations = new HashMap<>();

    static {
        priorityTranslations.put(Priority.LOW, "Bajo");
        priorityTranslations.put(Priority.MEDIUM, "Medio");
        priorityTranslations.put(Priority.HIGH, "Alto");
        priorityTranslations.put(Priority.CRITICAL, "Crítico");

        statusTranslations.put(Status.OPEN, "Pendiente");
        statusTranslations.put(Status.IN_PROGRESS, "En Progreso");
        statusTranslations.put(Status.COMPLETED, "Completado");
        statusTranslations.put(Status.CANCELLED, "Cancelado");
    }

    public static String getTranslatedPriority(Priority priority) {
        return priorityTranslations.getOrDefault(priority, priority.name());
    }

    public static String getTranslatedStatus(Status status) {
        return statusTranslations.getOrDefault(status, status.name());
    }

    public static Priority getPriorityFromTranslation(String translation) {
        return priorityTranslations.entrySet().stream()
                .filter(entry -> entry.getValue().equals(translation))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public static Status getStatusFromTranslation(String translation) {
        return statusTranslations.entrySet().stream()
                .filter(entry -> entry.getValue().equals(translation))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}

