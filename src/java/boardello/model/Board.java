/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardello.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  private long accountId;
  private String name;
  private String slug;
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastActivityAt;
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastViewedAt;

  public Board() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
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
