package pl.com.bottega.cinemamanagement.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Bartosz on 2016-09-19.
 */
public class Calculation {

    private BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO);
    private Set<TicketOrder> tickets;

    public Calculation(Set<TicketOrder> tickets, Set<TicketPrice> prices) {
        checkNotNull(tickets);
        checkNotNull(prices);

        this.tickets = tickets;
        calculatePrices(prices);
    }

    private void calculatePrices(Set<TicketPrice> ticketPrices) {
        for (TicketOrder ticket : tickets) {
            TicketPrice price = getTicketPrice(ticketPrices, ticket.getKind());
            ticket.setUnitPrice(price.getPrice());
            ticket.calculateTotalPrice();
            totalPrice = totalPrice.add(ticket.getTotalPrice());
        }
    }

    private TicketPrice getTicketPrice(Set<TicketPrice> price, String ticketType) {
        for (Iterator<TicketPrice> it = price.iterator(); it.hasNext(); ) {
            TicketPrice ticketPrice = it.next();
            if (ticketPrice.getType().equals(ticketType))
                return ticketPrice;
        }
        throw new IllegalStateException("Ticket does not exists");
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Set<TicketOrder> getTickets() {
        return tickets;
    }
}
