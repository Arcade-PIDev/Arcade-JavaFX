/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arcade.Entities;

/**
 *
 * @author Amira
 */
public class Commande {
    private int id,prixTotal;
    private boolean isCanceled, isPaid;
    
    public Commande() {
    }

    public Commande(int id,int  prixTotal, boolean isCanceled, boolean isPaid) {
        this.prixTotal = prixTotal;
        this.id = id;
        this.isCanceled = isCanceled;
        this.isPaid = isPaid;    }

    public boolean isIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", prixTotal=" + prixTotal + ", isCanceled=" + isCanceled + ", isPaid=" + isPaid + '}';
    }
    
}
