package backend.academy;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Renderer {

    private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    private final SecureRandom random = new SecureRandom(); // Более безопасный генератор случайных чисел
    private final List<String> transformations = List.of(
        "Disk Transformation",
        "Heart Transformation",
        "Hyperbolic Transformation",
        "Linear Transformation",
        "Polar Transformation",
        "Sine Transformation",
        "Spherical Transformation"
    );

    /**
     * Отображение главного меню
     */
    public void displayMenu() {
        log.info("Fractal Flame Renderer");
        log.info("Available Transformations:");
        for (int i = 0; i < transformations.size(); i++) {
            log.info("{} - {}", i + 1, transformations.get(i));
        }
        log.info("Select transformations (comma-separated, e.g., 1,3,5):");
    }

    /**
     * Получение выбранных преобразований от пользователя
     */
    public List<String> getSelectedTransformations() {
        String input = scanner.nextLine();
        List<String> selected = new ArrayList<>();
        for (String index : input.split(",")) {
            try {
                int idx = Integer.parseInt(index.trim()) - 1;
                if (idx >= 0 && idx < transformations.size()) {
                    selected.add(transformations.get(idx));
                } else {
                    log.warn("Index out of range: {}", index.trim());
                }
            } catch (NumberFormatException e) {
                log.warn("Invalid input, skipping: {}", index.trim());
            }
        }
        return selected;
    }

    /**
     * Запрос целого числа у пользователя
     */
    public int promptForInt(String message) {
        while (true) {
            log.info(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                log.warn("Invalid number, please try again.");
            }
        }
    }

    /**
     * Запрос логического значения у пользователя
     */
    public boolean promptForBoolean(String message) {
        log.info("{} (y/n):", message);
        String input = scanner.nextLine().trim().toLowerCase();
        return "y".equals(input) || "yes".equals(input);
    }

    /**
     * Логирование завершения рендеринга с путем сохраненного изображения
     */
    public void displayResult(String imagePath) {
        log.info("Rendering completed. Image saved to: {}", imagePath);
    }

    /**
     * Закрытие ресурсов
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
            log.info("Scanner closed successfully.");
        }
    }

    /**
     * Генерация случайного числа через безопасный генератор
     */
    public int getSecureRandomInt(int bound) {
        return random.nextInt(bound);
    }
}
