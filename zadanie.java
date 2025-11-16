    import java.io.*;  // File, FileWriter, BufferedWriter, BufferedReader, IOException
    import java.time.*; // LocalDateTime
    import java.time.format.DateTimeFormatter; // formatowanie daty i godziny
    import java.util.Scanner;

    class Main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int wybor = 0;

            while (wybor != 4) {
                System.out.println("Menu programu:");
                System.out.println("1. Dodaj nowy wydatek");
                System.out.println("2. Pokaż historię wydatków");
                System.out.println("3. Oblicz sumę wydatków");
                System.out.println("4. Wyjdź");
                System.out.print("Twój wybór: ");

                wybor = Integer.parseInt(scanner.nextLine());

                switch (wybor) {
                    case 1:
                        dodajWydatek(scanner);
                        break;
                    case 2:
                        pokazWydatki();
                        break;
                    case 3:
                        sumujWydatki();
                        break;
                    case 4:
                        System.out.println("Koniec programu.");
                        break;
                    default:
                        System.out.println("Niepoprawny wybór, spróbuj ponownie.");
                }
            }

            scanner.close();
        }

        // Metoda dodawania wydatku
        public static void dodajWydatek(Scanner scanner) {
            try {
                System.out.println("Podaj nazwę/opis wydatku: ");
                String opis = scanner.nextLine();

                System.out.println("Podaj kategorię: ");
                String kategoria = scanner.nextLine();

                System.out.println("Podaj kwotę zmiennoprzecinkową: ");
                String kwotaTekst = scanner.nextLine();
                double kwota = Double.parseDouble(kwotaTekst);

                if (kwota <= 0) {
                    throw new IllegalArgumentException("Kwota musi być większa od 0");
                }

                // Pobranie aktualnej daty i godziny
                LocalDateTime teraz = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String dataCzas = teraz.format(formatter);

                // Przygotowanie linii do zapisu w pliku
                String linia = "[" + dataCzas + "] – Kategoria: " + kategoria +
                        " – Kwota: " + String.format("%.2f", kwota) +
                        " – Opis: " + opis;

                // Zapis do pliku w try-with-resources
                // BufferedWriter "buforuje" zapis, czyli zapisuje partiami, szybciej niż zwykły FileWriter
                try (FileWriter fw = new FileWriter("wydatki.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(linia);
                    bw.newLine(); // dodaje nową linię po wpisie
                }

            } catch (NumberFormatException e) {
                System.out.println("Błędny format liczby: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Błąd zapisu do pliku: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Operacja zakończona pomyślnie.");
            }
        }

        // Metoda wyświetlania wszystkich wydatków
        public static void pokazWydatki() {
            File plik = new File("wydatki.txt");

            // Jeśli plik nie istnieje, tworzymy go i informujemy użytkownika
            if (!plik.exists()) {
                try {
                    plik.createNewFile();
                } catch (IOException e) {
                    System.out.println("Błąd przy tworzeniu pliku: " + e.getMessage());
                    return;
                }
                System.out.println("Brak wydatków do wyświetlenia.");
                return;
            }

            // Odczyt pliku linia po linii
            try (BufferedReader br = new BufferedReader(new FileReader(plik))) {
                String linia;
                boolean pusty = true;
                while ((linia = br.readLine()) != null) {
                    System.out.println(linia);
                    pusty = false;
                }
                if (pusty) {
                    System.out.println("Brak wydatków do wyświetlenia.");
                }
            } catch (IOException e) {
                System.out.println("Błąd odczytu pliku: " + e.getMessage());
            } finally {
                System.out.println("Operacja zakończona pomyślnie.");
            }
        }
    //--------------------------------------------------------------------------------
        // Metoda sumowania wszystkich wydatków
        public static void sumujWydatki() {
            File plik = new File("wydatki.txt");

            if (!plik.exists()) {
                try {
                    plik.createNewFile();
                } catch (IOException e) {
                    System.out.println("Błąd przy tworzeniu pliku: " + e.getMessage());
                    return;
                }
                System.out.println("Brak wydatków do obliczenia.");
                return;
            }

            double suma = 0.0;

            try (BufferedReader br = new BufferedReader(new FileReader(plik))) {
                String linia;
                boolean pusty = true;

                while ((linia = br.readLine()) != null) {
                    pusty = false;
                    if (linia.contains("Kwota:")) {
                        try {
                            // Podział po "Kwota:" i pobranie liczby
                            String[] czesci = linia.split("Kwota:");
                            // split("–")[0] bierze część przed kolejnym myślnikiem
                            String kwotaStr = czesci[1].split("–")[0].trim();
                            double kwota = Double.parseDouble(kwotaStr);
                            suma += kwota;
                        } catch (NumberFormatException e) {
                            System.out.println("Nieprawidłowa kwota w linii: " + linia);
                        }
                    }
                }

                if (pusty) {
                    System.out.println("Brak wydatków do obliczenia.");
                } else {
                    System.out.printf("Łączna suma wydatków: %.2f zł%n", suma);
                }

            } catch (IOException e) {
                System.out.println("Błąd odczytu pliku: " + e.getMessage());
            } finally {
                System.out.println("Operacja zakończona pomyślnie.");
            }
        }
    }
