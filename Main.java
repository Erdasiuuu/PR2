import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static final int ADD_EMPTY_OBJECT = 1;
    private static final int ADD_OBJECT = 2;
    private static final int EDIT_OBJECT = 3;
    private static final int OUTPUT_LIST = 4;
    private static final int SORT_LIST = 5;
    private static final int EXIT = 6;
    
	public static void main(String[] args) {
        List<Book> book = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != EXIT) {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case ADD_EMPTY_OBJECT:
                    book.add(new Book());
                    System.out.printf("\nДобавлена книга со значениями по умолчанию\n\n");
                    break;
                case ADD_OBJECT:
                    Book tmp = new Book();
                    tmp.modify();
                    book.add(tmp);
                    break;
                case EDIT_OBJECT:
                    prepareForEdit(book, scanner);
                    break;
                case OUTPUT_LIST:
                    output(book);
                    break;
                case SORT_LIST:
                    break;
                case EXIT:
                    break;
                default:
                    printErrorInput();
                    break;
            }
        }
	}

  public static void output(List<Book> book){
      int size = book.size();
      checkListSize(size);
      for (int i = 0; i < size; i++) {
          System.out.printf("\n%d: %s\n", i + 1, book.get(i));
      }
      if (size != 0) {
        System.out.printf("\n");
      }
  }
  
  public static void prepareForEdit(List<Book> book, Scanner scanner) {
      int choice = 0;
      int size = book.size();
        checkListSize(size);
        if (size != 0) {
            choice = getBookIndex(size, scanner);
            book.get(choice - 1).modify();
        }

  }
  
  public static void printEditMenu() {
    System.out.printf("Введите один из вариантов меню.\n");
    System.out.printf("1. Добавить пустой объект к массиву \n");
    System.out.printf("2. Добавить объект к массиву, заполненный вручную\n");
    System.out.printf("3. Редактировать значения\n");
    System.out.printf("4. Вывод информации про все объекты\n");
    System.out.printf("5. Сортировка массива\n");
    System.out.printf("6. Завершение программы\n");
  }
  
  public static void checkListSize(int size) {
      if (size == 0) {
            System.out.printf("\nНет введенных данных\n\n");
      }
  }
  
  public static int getBookIndex(int size, Scanner scanner) {
      int choice = 0;
      while (choice <= 0 || choice > size) {
          printIndexFindMenu(size);
          choice = scanner.nextInt();
          scanner.nextLine();
          if (choice <= 0 || choice > size) {
              printErrorInput();
          }
      }
      return choice;
  }

  public static void printIndexFindMenu(int size) {
    System.out.printf("\nВведите номер книги. Общее количество книг: %d. Отсчет начинается с 1\n", size);
  }

  public static void printMenu() {
    System.out.printf("Введите один из вариантов меню.\n");
    System.out.printf("1. Добавить пустой объект к массиву \n");
    System.out.printf("2. Добавить объект к массиву, заполненный вручную\n");
    System.out.printf("3. Редактировать значения\n");
    System.out.printf("4. Вывод информации про все объекты\n");
    System.out.printf("5. Сортировка массива\n");
    System.out.printf("6. Завершение программы\n");
  }

  public static void printErrorInput() {
    System.out.printf("\nНеверный ввод. Попробуйте еще раз\n");
  }
	
	public static class Book {
	    private String title;
	    private String genre;
	    private int pages;
	    private double wordPerPage;
	    private Scanner scanner = new Scanner(System.in);
	    
	    public Book() {
	        title = "title";
	        genre = "genre";
	        pages = 100;
	        wordPerPage = 100;
	    }
	    
	    public Book(String title, String genre, int pages, double wordPerPage) {
	        this.title = title;
	        this.genre = genre;
	        this.pages = pages;
	        this.wordPerPage = wordPerPage;
	    }
	    
	 
	    
	    public String getTitle() {
	        return title;
	    }
	    
	    public void setTitle(String title) {
	        this.title = title;
	    }
	    
	    public String getGenre() {
	        return genre;
	    }
	    
	    public void setGenre(String genre) {
	        this.genre = genre;
	    }
	    
	    public int getPages() {
	        return pages;
	    }
	    
	    public void setPages(int pages) {
	        this.pages = pages;
	    }
	    
	    public double getWordPerPage() {
	        return wordPerPage;
	    }
	    
	    public void setWordPerPage(double wordPerPage) {
	        this.wordPerPage = wordPerPage;
	    }
	    
	    public void modify() {
	        int choice = 0;
	        while (choice != 6) {
	            printAttributes();
                choice = scanner.nextInt();
                scanner.nextLine();
	            switch (choice) {
	                case 1:
                        checkTitle();
	                    break;
	                case 2:
	                    checkGenre();
	                    break;
	                case 3:
	                    checkPages();
	                    break;
	                case 4:
	                    checkWordPerPage();
	                    break;
	                case 5:
	                    currentData();
	                    break;
	                case 6:
	                    break;
	                default:
	                    printErrorInput();
	                    break;
	            }
	        }
	    }
	    
	    private void checkTitle() {
            String title = scanner.nextLine();
            if (title.length() == 0) {
                System.out.printf("%s %s", incorrectStr(), this.title);
            }
            else {
                setTitle(title);
            }
	    }
	    
	    public void currentData() {
	        System.out.printf("\n%s\n", this);
	    }
	    
	    private void checkGenre() {
            String genre = scanner.nextLine();
            if (genre.length() == 0) {
                System.out.printf("%s %s", incorrectStr(), this.genre);
            }
            else {
                setGenre(genre);
            }
	    }
	    
	    private String incorrectStr() {
	        return "Введена пустая строка, Оставлены предыдущие данные:";
	    }
	    
	    private void checkPages() {
	        int pages = scanner.nextInt();
            scanner.nextLine();
            if (pages <= 0) {
                System.out.printf("%s %d", incorrectNum(), this.pages);
            }
            else {
                setPages(pages);
            }
	    }
	    
	    private void checkWordPerPage() {
	        double wordPerPage = scanner.nextDouble();
            scanner.nextLine();
            if (wordPerPage <= 0) {
                System.out.printf("%s %f", incorrectNum(), this.wordPerPage);
            }
            else {
                setWordPerPage(wordPerPage);
            }	        
	    }
	    
	    private String incorrectNum() {
	        return "Введено число меньшее или равное нулю, Оставлены предыдущие данные:";
	    }
	    
	    public void printAttributes() {
            System.out.printf("\n1. Изменить название книги\n");
            System.out.printf("2. Изменить жанр\n");
            System.out.printf("3. Изменить количество страниц\n");
            System.out.printf("4. Изменить количестов слов на страницу\n");
            System.out.printf("5. Вывести текущие данные\n");
            System.out.printf("6. Закончить ввод\n");
	    }
	    
	    private double wordCount(int pages, double wordPerPage) {
	        return pages * wordPerPage;
	    }
	    
	    @Override
	    public String toString() {
	        return "Title: " + title + " Genre: " + genre + " Pages: " + pages + " wordPerPage: " + wordPerPage + " wordCount " + wordCount(pages, wordPerPage);
	    }
	}
}
