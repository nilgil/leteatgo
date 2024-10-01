package common.auth.jwt;

import java.util.List;
import java.util.UUID;

public interface Account {

  AccountType accountType();

  UUID id();

  List<String> roles();
}
