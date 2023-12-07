import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class day1 {
    public static void main(String[] args) {
        ArrayList<String> lines = read_in_file(args[0]);
        int result = extract_number(lines);
        System.out.println(result);
    }

    public static ArrayList<String> read_in_file(String filename) {
        ArrayList<String> lines = new ArrayList<String>();
        File inputFile = new File(filename);
        try{
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            scanner.close();

            return lines;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    }

    public static int extract_number(ArrayList<String> lines) {
        Map<String, Integer> words_to_nums_map = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
        );
        int result = 0;
        
        for (String line : lines) {
            boolean front_found = false;
            boolean back_found = false;
            for (int front=0; front < line.length(); front++) {
                for (String word : words_to_nums_map.keySet()) {
                    if (line.substring(0, front).contains(word)) {
                        result = result + 10 * words_to_nums_map.get(word);
                        front_found = true;
                        break;
                    }
                }
                if (front_found) {
                    break;
                }
                if (Character.isDigit(line.charAt(front))) {
                    result = result + 10 * Character.getNumericValue(line.charAt(front));
                    break;
                }
            }

            for (int back=line.length()-1; back >=0; back--) {
                for (String word : words_to_nums_map.keySet()) {
                    if (line.substring(back, line.length()).contains(word)) {
                        result = result + words_to_nums_map.get(word);
                        back_found = true;
                        break;
                    }
                }
                if (back_found) {
                    break;
                }
                if (Character.isDigit(line.charAt(back))) {
                    result = result + Character.getNumericValue(line.charAt(back));
                    break;
                }
            }
        }
        return result;
    }

}
