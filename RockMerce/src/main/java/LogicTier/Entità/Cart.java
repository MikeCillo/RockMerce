package LogicTier.Entità;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Collection;

public class Cart {

    private int id;

    private double tempTotal=0.00;


    private int numGuitars=0;


    // interna: mappa per lookup rapido tramite id
    private final Hashtable<Integer, Guitar> guitarsMap = new Hashtable<>();

    // lista esposta (ma sincronizzata con la mappa tramite la classe interna SyncList)
    private List<Guitar> guitars = new SyncList();


    public void addGuitar(Guitar guitar){
        // mantieni ordine di inserimento nella lista ed update la mappa
        this.guitars.add(guitar);
        if (guitar != null) guitarsMap.put(guitar.getId(), guitar);
        // tempTotal e numGuitars come prima
        this.tempTotal=tempTotal+guitar.getPrice();
        this.numGuitars+=guitar.getDisponibility();
    }

    public Guitar removeGuitarUser(int pos){
        if(pos>=0 && pos < this.guitars.size()) {
            Guitar guitar= this.guitars.remove(pos);
            if (guitar != null) {
                this.tempTotal = tempTotal - guitar.getPrice();
                this.numGuitars -= guitar.getDisponibility();
                guitarsMap.remove(guitar.getId());
            }
            return guitar;
        }
        else {
            return null;
        }
    }

    public Guitar removeGuitar(int pos){
        // qui 'pos' rappresenta l'id della chitarra
        if(pos>=0) {
            // usa la mappa per lookup rapido
            Guitar guitar = this.guitarsMap.get(pos);
            if (guitar != null) {
                // rimuovi anche dalla lista (rimuove il primo occorrenza)
                this.guitars.remove(guitar);
                this.tempTotal = tempTotal - guitar.getPrice();
                this.numGuitars -= guitar.getDisponibility();
                guitarsMap.remove(guitar.getId());
                return guitar;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTempTotal() {
        return tempTotal;
    }

    public void setTempTotal(double tempTotal) {
        this.tempTotal = tempTotal;
    }

    public int getNumGuitars() {
        return this.numGuitars;
    }

    public void setNumGuitars(int numItems) {
        this.numGuitars = numItems;
    }

    // Conserviamo la firma: restituisce ArrayList<Guitar>
    @SuppressWarnings("unchecked")
    public ArrayList<Guitar> getGuitars() {
        // ritorniamo la lista esposta come ArrayList per compatibilità; se il codice prova a fare cast, lo possa fare
        // Tuttavia la nostra lista è una SyncList che estende ArrayList tramite implementazione interna, quindi il cast è sicuro.
        return (ArrayList<Guitar>) this.guitars;
    }

    public void setGuitars(ArrayList<Guitar> guitars) {
        // assegna la lista esterna (manteniamo la stessa istanza per assertSame nei test)
        this.guitarsMap.clear();
        if (guitars == null) {
            this.guitars = new SyncList();
        } else {
            this.guitars = guitars;
            // popola la mappa con gli elementi della lista fornita
            for (Guitar g : guitars) if (g != null) this.guitarsMap.put(g.getId(), g);
        }
    }

    /**
     * Lista interna che sincronizza automaticamente la guitarsMap quando viene modificata.
     * Estendiamo ArrayList per mantenere compatibilità con cast e con API che si aspettano ArrayList.
     */
    private class SyncList extends ArrayList<Guitar> {
        @Override
        public boolean add(Guitar g) {
            boolean r = super.add(g);
            if (r && g != null) guitarsMap.put(g.getId(), g);
            return r;
        }

        @Override
        public void add(int index, Guitar element) {
            super.add(index, element);
            if (element != null) guitarsMap.put(element.getId(), element);
        }

        @Override
        public boolean addAll(Collection<? extends Guitar> c) {
            boolean r = super.addAll(c);
            if (r) {
                for (Guitar g : c) if (g != null) guitarsMap.put(g.getId(), g);
            }
            return r;
        }

        @Override
        public boolean remove(Object o) {
            if (o instanceof Guitar) {
                Guitar g = (Guitar) o;
                guitarsMap.remove(g.getId());
            }
            return super.remove(o);
        }

        @Override
        public Guitar remove(int index) {
            Guitar g = super.remove(index);
            if (g != null) guitarsMap.remove(g.getId());
            return g;
        }

        @Override
        public void clear() {
            super.clear();
            guitarsMap.clear();
        }

        @Override
        public Guitar set(int index, Guitar element) {
            Guitar old = super.set(index, element);
            if (old != null) guitarsMap.remove(old.getId());
            if (element != null) guitarsMap.put(element.getId(), element);
            return old;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            for (Object o : c) if (o instanceof Guitar) guitarsMap.remove(((Guitar) o).getId());
            return super.removeAll(c);
        }
    }

}
