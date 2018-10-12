package com.travelapp.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note")
    private String note;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "booking_ticket",
               joinColumns = @JoinColumn(name = "bookings_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tickets_id", referencedColumnName = "id"))
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("bookings")
    private Customer customer;

    @ManyToOne
    @JsonIgnoreProperties("bookings")
    private Tour tour;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public Booking note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public Booking tickets(Set<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    public Booking addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.getBookings().add(this);
        return this;
    }

    public Booking removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
        ticket.getBookings().remove(this);
        return this;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Booking customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Tour getTour() {
        return tour;
    }

    public Booking tour(Tour tour) {
        this.tour = tour;
        return this;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Booking booking = (Booking) o;
        if (booking.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), booking.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Booking{" +
            "id=" + getId() +
            ", note='" + getNote() + "'" +
            "}";
    }
}
