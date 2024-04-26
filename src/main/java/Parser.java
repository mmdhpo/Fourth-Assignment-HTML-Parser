import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class Parser {
    static List<Country> countries = new ArrayList<>();
    static ArrayList<String> name = new ArrayList<>();
    static ArrayList<String> capital = new ArrayList<>();
    static ArrayList<Integer> population = new ArrayList<>();
    static ArrayList<Double> area = new ArrayList<>();

    public static List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        int n = sortedByName.size();
        sortedByName.sort(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return  sortedByName;
    }

    public static List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        int n = sortedByPopulation.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedByPopulation.get(j).getPopulation() < sortedByPopulation.get(j + 1).getPopulation()) {
                    Country temp = sortedByPopulation.get(j);
                    sortedByPopulation.set(j, sortedByPopulation.get(j + 1));
                    sortedByPopulation.set(j + 1, temp);
                }
            }
        }
        return sortedByPopulation;
    }

    public static List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        int n = sortedByArea.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedByArea.get(j).getArea() < sortedByArea.get(j + 1).getArea()) {
                    Country temp = sortedByArea.get(j);
                    sortedByArea.set(j, sortedByArea.get(j + 1));
                    sortedByArea.set(j + 1, temp);
                }
            }
        }
        return sortedByArea;
    }

    public static void setUp() throws IOException {
        File input = new File("C:\\Users\\Lenovo\\Desktop\\IdeaProjects\\Fourth-Assignment-HTML-Parser\\src\\Resources\\country-list.html");
        Document doc = Jsoup.parse(input, "UTF-8");
        Elements extract = doc.select(".country");
        for(Element i: extract)
        {
            name.add(i.select(".country-name").text());
            capital.add(i.select(".country-capital").text());
            population.add(Integer.parseInt(i.select(".country-population").text()));
            area.add(Double.parseDouble(i.select(".country-area").text()));
        }
        for(int i = 0; i < name.size(); ++i){
            Country country = new Country(name.get(i), capital.get(i), population.get(i), area.get(i));
            countries.add(country);
        }
    }

    public static void main(String[] args) throws IOException {
        setUp();
        List<Country> sortedCountryList = new ArrayList<>();
        System.out.println("How do you want to sort the countries list:\n1.By name\n2.By Population\n3.By area");
        Scanner userInput = new Scanner(System.in);
        switch (userInput.nextInt()){
            case 1:
                sortedCountryList = sortByName();
                break;
            case 2:
                sortedCountryList = sortByPopulation();
                break;
            case 3:
                sortedCountryList = sortByArea();
                break;
        }
        for(Country i: sortedCountryList)
            System.out.println(i.toString());
    }

}
