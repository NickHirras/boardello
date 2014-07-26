/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boardello.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nicholas.e.smith
 */
@Entity
@XmlRootElement
public class Board implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    @ManyToOne
    private Account account;
    private String name;
    private String slug;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivityAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastViewedAt;
    @OneToMany(mappedBy = "board")
    private Collection<Deck> decks;
    @OneToMany(mappedBy =  "board")
    private Collection<Label> labels;

    public Board() {
        
    }
    
    public Board(Long id, Account account, String name, String slug, Date lastActivityAt, Date lastViewedAt, Collection<Deck> decks) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.slug = slug;
        this.lastActivityAt = lastActivityAt;
        this.lastViewedAt = lastViewedAt;
        this.decks = decks;
    }
    
    public Board(Long id, String name, String slug, Date lastActivityAt, Date lastViewedAt) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.lastActivityAt = lastActivityAt;
        this.lastViewedAt = lastViewedAt;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(Date lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    public Date getLastViewedAt() {
        return lastViewedAt;
    }

    public void setLastViewedAt(Date lastViewedAt) {
        this.lastViewedAt = lastViewedAt;
    }     

    public Collection<Deck> getDecks() {
        return decks;
    }

    public void setDecks(Collection<Deck> decks) {
        this.decks = decks;
    }
    
    public Collection<Label> getLabels() {
        return labels;
    }
    
    public void setLabels(Collection<Label> labels) {
        this.labels = labels;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Board)) {
            return false;
        }
        Board other = (Board) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "boardello.model.Board[ id=" + id + " ]";
    }
    
}
