package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.acme.constants.METADATA
import org.acme.dto.book.BookFullDTO
import org.acme.dto.order.OrderFullDTO
import org.acme.dto.order.OrderSaveDTO
import org.acme.mapper.toBook
import org.acme.mapper.toFullDTO
import org.acme.mapper.toOrder
import org.acme.model.Book
import org.acme.model.OrderItem
import org.acme.model.User
import org.acme.repository.OrderRepository

@ApplicationScoped
class OrderService {
    @Inject
    lateinit var repository: OrderRepository
    @Inject
    lateinit var bookService: BookService
    @Inject
    lateinit var userService: UserService

    @Transactional
    fun saveOrder(orderForSave: OrderSaveDTO): OrderFullDTO {
        val userID = orderForSave.userId

        val user: User =
            userService.getById(userID) ?:
            throw NotFoundException("Unknown user attempted to order books. " +
                    "User id: $userID")

        val orderEntity = orderForSave.toOrder()
        orderEntity.user = user
        val orderItems: List<OrderItem> = orderForSave.items.map {
           OrderItem(
                id = null,
                book = bookService.getById(it.bookId)?.toBook() ?: throw NotFoundException(
                    "Book with id ${it.bookId} referenced in order not found in db."
                ),
                order = orderEntity,
                amount = it.amount,
                metadata = METADATA)
        }

        orderEntity.items = orderItems.toMutableList()
        repository.persist(orderEntity)
        return orderEntity.toFullDTO()
    }
}