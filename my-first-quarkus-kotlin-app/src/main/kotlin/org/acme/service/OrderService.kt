package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.acme.dto.book.BookFullDTO
import org.acme.dto.order.OrderFullDTO
import org.acme.dto.order.OrderSaveDTO
import org.acme.mapper.toFullDTO
import org.acme.mapper.toOrder
import org.acme.model.Book
import org.acme.repository.OrderRepository

@ApplicationScoped
class OrderService {
    @Inject
    lateinit var repository: OrderRepository
    @Inject
    lateinit var bookService: BookService

    @Transactional
    fun saveOrder(orderForSave: OrderSaveDTO): OrderFullDTO {
        val bookIds: List<Long> = orderForSave.books.map { it.id }

        val books: List<BookFullDTO> =
            bookIds.map { bookService.getById(it) ?:
            throw NotFoundException("Book with id $it " +
                    "referenced in order not found in db.") }

        val orderEntity = orderForSave.toOrder()
        repository.persist(orderEntity)
        return orderEntity.toFullDTO()
    }
}