package backend.academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Renderer {
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> transformations = List.of(
        "Disk Transformation",
        "Heart Transformation",
        "Hyperbolic Transformation",
        "Linear Transformation",
        "Polar Transformation",
        "Sine Transformation",
        "Spherical Transformation"
    );

    public void displayMenu() {
        StringBuilder menu = new StringBuilder("Fractal Flame Renderer\nAvailable Transformations:\n");
        for (int i = 0; i < transformations.size(); i++) {
            menu.append(String.format("%d. %s%n", i + 1, transformations.get(i)));
        }
        menu.append("Select transformations (comma-separated, e.g., 1,3,5):");
        System.out.println(menu);
    }

    public List<String> getSelectedTransformations() {
        String input = scanner.nextLine();
        List<String> selected = new ArrayList<>();
        for (String index : input.split(",")) {
            try {
                int idx = Integer.parseInt(index.trim()) - 1;
                if (idx >= 0 && idx < transformations.size()) {
                    selected.add(transformations.get(idx));
                }
            } catch (NumberFormatException ignored) {
                // Ошибка ввода подавлена.
            }
        }
        return selected;
    }

    public int promptForInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) {
                // Повторяем запрос.
            }
        }
    }

    public boolean promptForBoolean(String message) {
        System.out.print(message + " (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    public void closeScanner() {
        scanner.close();
    }
}
