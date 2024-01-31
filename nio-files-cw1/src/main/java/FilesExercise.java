import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.StringTemplate.STR;

class FilesExercise {
    private static final String OUTPUT_DIR = "output";
    private static final String FILENAME_TEMPLATE = "file-%d.txt";
    private static final String FILENAME_PATTERN = "file-([1-9]|10).txt";
    private static final int NO_OF_FILES_MIN = 1;
    private static final int NO_OF_FILES_MAX = 10;

    public static void main(String[] args) {
        int filesNumber = readNumberOfFiles();
        if (filesNumber >= NO_OF_FILES_MIN && filesNumber <= NO_OF_FILES_MAX) {
            try {
                Path outputDirectory = createOutputDirectory(OUTPUT_DIR);
                cleanOutputDirectory(outputDirectory);
                createFiles(outputDirectory, filesNumber);
                System.out.println("Pliki zostały utworzone");
            } catch (IOException e) {
                System.err.println(STR."Nie można utworzyć katalogu \{OUTPUT_DIR}");
            }
        } else {
            System.out.println(
                    STR."Liczba plików powinna mieścić się w przedziale [\{NO_OF_FILES_MIN}; \{NO_OF_FILES_MAX}]"
            );
        }
    }

    private static int readNumberOfFiles() {
        System.out.println("Ile plików chcesz stworzyć?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static Path createOutputDirectory(String outputDir) throws IOException {
        Path dirPath = Path.of(outputDir);
        return Files.createDirectories(dirPath);
    }

    private static void cleanOutputDirectory(Path outputDirectory) {
        try {
            Files.walkFileTree(outputDirectory, Collections.emptySet(), 1, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileName = file.getFileName().toString();
                    if (fileName.matches(FILENAME_PATTERN)) {
                        Files.delete(file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println(STR."Błąd podczas czyszczenia katalogu \{outputDirectory}");
            System.err.println(e.getMessage());
        }
    }

    private static void createFiles(Path directory, int filesNumber) {
        for (int i = 1; i < filesNumber + 1; i++) {
            Path filePath = Path.of(FILENAME_TEMPLATE.formatted(i));
            Path fullFilePath = directory.resolve(filePath);
            try {
                Files.createFile(fullFilePath);
                writeFileContent(fullFilePath, i);
            } catch (IOException e) {
                System.err.println(STR."Wystąpił błąd podczas tworzenia pliku \{fullFilePath}");
            }
        }
    }

    private static void writeFileContent(Path file, int numberOfLines) {
        List<String> lines = IntStream.rangeClosed(1, numberOfLines)
                .mapToObj(Integer::toString)
                .toList();
        try {
            Files.write(file, lines);
        } catch (IOException e) {
            System.err.println(STR."Nie udało się zapisać zawartości do pliku \{file}");
        }
    }
}
