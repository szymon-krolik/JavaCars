package com.cars;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* !!nazwa pliku z ktorego importujemy pojazdy to "cars.txt"!! */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Pojazd> pojazdy = null;// ArrayLista działa podobnie jak tablica ale może zmieniać swój rozmiar

        
        try{ // Spróbuj wczytać adresy z pliku, jeśli nie uda się go znaleźć po prostu stwórz nową liste
           FileInputStream fis = new FileInputStream(new File("pojazdy"));
           ObjectInputStream ois = new ObjectInputStream(fis);
           
           pojazdy = (ArrayList<Pojazd>) ois.readObject(); // Serializable pozwala na takie odczytywanie całych list intancji danej klasy
           
           fis.close();
           ois.close();
        }catch(FileNotFoundException e)
        {
            pojazdy = new ArrayList();
        }
        String wybor = "";
        while(!wybor.equals("8"))
        {
            System.out.println("Wypożyczalnia pojazdów");
            System.out.println("___________________________");
            System.out.println("1. Wyświetl");
            System.out.println("2. Dodaj");
            System.out.println("3. Edytuj");
            System.out.println("4. Usuń");
            System.out.println("5. Importuj");
            System.out.println("6. Szukaj");
            System.out.println("7. Zapisz");
            System.out.println("8. Wyjdź");
            
            wybor = scanner.nextLine();
            
            switch(wybor)//wybór akcji po wybraniu menu
            {
                case "1":
                    wyswietl(pojazdy, scanner);
                break;
                case "2":
                    dodaj(pojazdy, scanner);
                break;
                case "3":
                    edytuj(pojazdy, scanner);
                break;
                case "4":
                    usun(pojazdy, scanner);
                break;
                case "5":
                    importuj(pojazdy, scanner);
                break;
                case "6":
                    szukaj(pojazdy, scanner);
                break;
                case "7":
                    zapisz(pojazdy, scanner);
                break;
            }
        }
    }

    private static void wyswietl(ArrayList<Pojazd> pojazdy, Scanner scanner) {
        System.out.println("Sortować po:");
        System.out.println("1. Marke");
        System.out.println("2. Modelu");
        System.out.println("3. Przebiegu");
        System.out.println("4. Roku produkcji");
        
        String sortBy = scanner.nextLine();
        
        switch(sortBy)//odpowiada za sortowanie pojazdow wedlug podanego kryterium
        {
            case "1":
                pojazdy.sort((x, y) -> x.getMarka().compareToIgnoreCase(y.getMarka()));
            break;
            case "2":
                pojazdy.sort((x, y) -> x.getModel().compareToIgnoreCase(y.getModel()));
            break;
            case "3":
                pojazdy.sort((x, y) -> x.getPrzebieg() - y.getPrzebieg());
            break;
            case "4":
                pojazdy.sort((x, y) -> x.getRokProdukcji() - y.getRokProdukcji());
            break;
        }
        
        for(int i = 0 ; i < pojazdy.size(); i++)
        {
            System.out.println(pojazdy.get(i));
        }
        scanner.nextLine();
    }

    private static void dodaj(ArrayList<Pojazd> pojazdy, Scanner scanner) {//dodawanie nowych pojazdów
        String rodzaj = "";
        while(!rodzaj.equals("1") && !rodzaj.equals("2") && !rodzaj.equals("3"))
        {
            System.out.println("Wybierz rodzaj pojazdu:");
            System.out.println("1. Samochód osobowy");
            System.out.println("2. Samochód ciężarowy");
            System.out.println("3. Motocykl");
            rodzaj = scanner.nextLine();
        }
        
        String marka = "";
        while(marka.equals(""))
        {
            System.out.println("Jakiej marki jest to pojazd?");
            marka = scanner.nextLine();
        }
        
        String model = "";
        while(model.equals(""))
        {
            System.out.println("Jaki to model pojazdu?");
            model = scanner.nextLine();
        }
        
        int przebieg = -1;
        while(przebieg < 0)
        {
            System.out.println("Jaki ma przebieg?");
            String przebiegstr = scanner.nextLine();
            
            try{
                przebieg = Integer.parseInt(przebiegstr);//parsowanie wejscia na INT
            }catch(NumberFormatException e)
            {
                przebieg = -1;
            }
        }
        
        int rokProdukcji = -1;
        while(rokProdukcji < 1886 || rokProdukcji > 2020)//warunek, ktory sprawdza poprawnosc rocznika pojazdu
        {
            System.out.println("Jaki jest jego rok produkcji?");
            String rokstr = scanner.nextLine();
            try{
                rokProdukcji = Integer.parseInt(rokstr);
            } catch(NumberFormatException e)
            {
                rokProdukcji = -1;
            }
        }
        
        
        switch(rodzaj)//switch, ktory zapisuje nowy pojazd
        {
            case "1":
                pojazdy.add(new Osobowy(marka, model, przebieg, rokProdukcji));
            break;
            case "2":
                pojazdy.add(new Ciezarowy(marka, model, przebieg, rokProdukcji));
            break;
            case "3":
                pojazdy.add(new Motocykl(marka, model, przebieg, rokProdukcji));
            break;
        }
        
        System.out.println("Dodano nowy pojazd");
        scanner.nextLine();
    }

    private static void edytuj(ArrayList<Pojazd> pojazdy, Scanner scanner) {//odpowiada za edycje pojazdu
        System.out.println("Wybierz numer pojazdu do edycji");
        for(int i = 0; i < pojazdy.size(); i++)
        {
            System.out.println(i +". " + pojazdy.get(i));
        }
        int indeks = -1;
        while(indeks < 0 || indeks >= pojazdy.size())//sprawdzenie czy podany pojazd istnieje
        {
            try{
                indeks = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e)
            {
                System.out.println("Niepoprawny numer pojazdu");
                return;
            }
        }
        
        System.out.println("Marka (jeśli bez zmian wcisnij enter)");
        String marka = scanner.nextLine();
        
        System.out.println("Model (jeśli bez zmian wcisnij enter)");
        String model = scanner.nextLine();
        
        int przebieg = -1;
        
        while(przebieg == -1)
        {
            try{
                System.out.println("Przebieg (jeśli bez zmian wcisnij enter)");
                String przebiegstr = scanner.nextLine();
                if(przebiegstr.equals(""))
                    break;
                przebieg = Integer.parseInt(przebiegstr);
            } catch(NumberFormatException e)
            {
                przebieg = -1;
            }
        }
        
        
        int rokProdukcji = -1;
        
        while(rokProdukcji < 1886 || rokProdukcji > 2020)
        {
            try{
                System.out.println("Rok produkcji (jeśli bez zmian wcisnij enter)");
                String rokProdukcjistr = scanner.nextLine();
                if(rokProdukcjistr.equals(""))
                    break;
                przebieg = Integer.parseInt(rokProdukcjistr);
            } catch(NumberFormatException e)
            {
                przebieg = -1;
            }
        }
        
        if(!marka.equals(""))
            pojazdy.get(indeks).setMarka(marka);
        
        if(!model.equals(""))
            pojazdy.get(indeks).setModel(model);
        
        if(przebieg != -1)
            pojazdy.get(indeks).setPrzebieg(przebieg);
        
        if(rokProdukcji != -1)
            pojazdy.get(indeks).setRokProdukcji(rokProdukcji);
        
        System.out.println("Zedytowano pojazd");
        scanner.nextLine();
        
    }

    private static void usun(ArrayList<Pojazd> pojazdy, Scanner scanner) {//usuwanie pojazdu z pliku
        System.out.println("Wybierz numer pojazdu do usuniecia");
        for(int i = 0; i < pojazdy.size(); i++)
        {
            System.out.println(i +". " + pojazdy.get(i));
        }
        int indeks = -1;
        while(indeks < 0 || indeks >= pojazdy.size())//obsluga bledow
        {
            try{
                indeks = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e)
            {
                System.out.println("Niepoprawny numer pojazdu");
                return;
            }
        }
        
        pojazdy.remove(pojazdy.get(indeks));
        System.out.println("Pomyślnie usunięto pojazd");
        scanner.nextLine();
    }

    private static void importuj(ArrayList<Pojazd> pojazdy, Scanner scanner) {//importowanie pojazdów z pliku
        System.out.println("Podaj nazwe pliku z danymi do zaimportowania");
        String filename = scanner.nextLine();
        int counter = 0;
        try{
            Scanner plik = new Scanner(new File(filename));
            ArrayList<Pojazd> tmp = new ArrayList();
            while(plik.hasNextLine())
            {
                String rodzaj = plik.nextLine().toLowerCase();
                switch(rodzaj)
                {
                    case "osobowy":
                        tmp.add(new Osobowy(plik.nextLine()));
                    break;
                    case "ciezarowy":
                        tmp.add(new Ciezarowy(plik.nextLine()));
                    break;
                    case "motocykl":
                        tmp.add(new Motocykl(plik.nextLine()));
                    break;
                }
                counter++;
            }
            plik.close();
            pojazdy.addAll(tmp);
            System.out.println("Zaimportowano " + counter + " pojazdów");
        }catch(FileNotFoundException e)//obsluga bledow przy obsługiwaniu pliku
        {
            System.out.println("Nie znaleziono pliku " + filename);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Błąd w trakcie importowania danych z pliku");
            System.out.println(e.getMessage());
        }
        
        scanner.nextLine();
    }

    private static void zapisz(ArrayList<Pojazd> pojazdy, Scanner scanner) throws FileNotFoundException, IOException {//odpowiada za zapisanie pojazdow do pliku
        FileOutputStream fos = new FileOutputStream("pojazdy", false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(pojazdy);
        fos.close();
        oos.close();
        
        System.out.println("Zapisano pojazdy");
        scanner.nextLine();
    }

    private static void szukaj(ArrayList<Pojazd> pojazdy, Scanner scanner) {//szukanie pojazdow po danym kryterium
        System.out.println("Po czym szukać?");
        System.out.println("1. Marce");
        System.out.println("2. Modelu");
        System.out.println("3. Przebiegu");
        System.out.println("4. Roku produkcji");
        
        String poCzym = scanner.nextLine();
        
        System.out.println("Wyszukaj:");
        String fraza = scanner.nextLine();
        
        for(int i = 0 ; i < pojazdy.size(); i++)
        {
            switch(poCzym)
            {
                case "1":
                    if(!pojazdy.get(i).getMarka().toLowerCase().contains(fraza.toLowerCase()))
                        continue;
                break;
                case "2":
                    if(!pojazdy.get(i).getModel().toLowerCase().contains(fraza.toLowerCase()))
                        continue;
                break;
                case "3":
                    if(!Integer.toString(pojazdy.get(i).getPrzebieg()).contains(fraza))
                        continue;
                break;
                case "4":
                    if(!Integer.toString(pojazdy.get(i).getRokProdukcji()).contains(fraza))
                        continue;
                break;
            }
            System.out.println(pojazdy.get(i));
        }
        
        scanner.nextLine();
    }
}
