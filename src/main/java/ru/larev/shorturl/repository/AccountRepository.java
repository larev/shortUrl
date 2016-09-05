package ru.larev.shorturl.repository;

import org.springframework.data.repository.CrudRepository;
import ru.larev.shorturl.entity.Account;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public interface AccountRepository extends CrudRepository<Account, String> {

}
