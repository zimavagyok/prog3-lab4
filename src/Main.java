import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Beer> beers;

    protected static void add(String[] cmd) {
        beers.add(new Beer(cmd[1],cmd[2],Double.parseDouble(cmd[3])));
    }

    protected static void list(String[] cmd) {
        if(cmd.length>1) {
            Comparator<Beer> cmp = null;
            switch(cmd[1]) {
                case "name":
                    cmp = new NameComparator();
                    break;
                case "style":
                    cmp = new StyleComparator();
                    break;
                case "strength":
                    cmp = new StrengthComparator();
                    break;
            }
            beers.sort(cmp);
        }
        for(Beer b : beers) {
            System.out.println(b.toString());
        }
    }

    protected static void save(String[] cmd) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cmd[1]));
            out.writeObject(beers);
            out.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected static void load(String[] cmd) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(cmd[1]));
            beers = (ArrayList<Beer>)in.readObject();
            in.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static void search(String[] cmd) {
        if(cmd.length>2) {
            if(cmd[1].equals("name")){
                for(Beer b : beers) {
                    if(b.get_name().equals(cmd[2])) System.out.println(b.toString());
                }
            } else if(cmd[1].equals("style")){
                for(Beer b : beers) {
                    if(b.get_style().equals(cmd[2])) System.out.println(b.toString());
                }
            } else if(cmd[1].equals("strength")){
                for(Beer b : beers) {
                    if(b.get_strength() == Double.parseDouble(cmd[2])) System.out.println(b.toString());
                }
            }
        } else {
            for(Beer b : beers) {
                if(b.get_name().equals(cmd[1])) System.out.println(b.toString());
            }
        }
    }

    protected static void find(String[] cmd) {
        if(cmd.length>2) {
            if(cmd[1].equals("name")){
                for(Beer b : beers) {
                    if(b.get_name().contains(cmd[2])) System.out.println(b.toString());
                }
            } else if(cmd[1].equals("style")){
                for(Beer b : beers) {
                    if(b.get_style().contains(cmd[2])) System.out.println(b.toString());
                }
            } else if(cmd[1].equals("strength")){
                for(Beer b : beers) {
                    if(b.get_strength() >= Double.parseDouble(cmd[2])) System.out.println(b.toString());
                }
            } else if (cmd[1].equals("weaker")) {
                for(Beer b : beers) {
                    if(b.get_strength() <= Double.parseDouble(cmd[2])) System.out.println(b.toString());
                }
            }
        } else {
            for(Beer b : beers) {
                if(b.get_name().contains(cmd[1])) System.out.println(b.toString());
            }
        }
    }

    protected static void delete(String[] cmd) {
        Iterator<Beer> i = beers.iterator();

        while(i.hasNext()) {
            Beer read = i.next();
            if(read.get_name().equals(cmd[1])) i.remove();
        }
    }

    public static void main(String[] args) throws IOException {
        beers = new ArrayList<Beer>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String line[] = br.readLine().split(" ");
            if(line[0].equals("exit")) {
                br.close();
                System.exit(0);
            } else if(line[0].equals("add")) {
                add(line);
            } else if(line[0].equals("list")) {
                list(line);
            } else if(line[0].equals("save")) {
                save(line);
            } else if(line[0].equals("load")) {
                load(line);
            } else if(line[0].equals("search")) {
                search(line);
            } else if(line[0].equals("find")) {
                find(line);
            } else if(line[0].equals("delete")) {
                delete(line);
            }
        }
    }
}
