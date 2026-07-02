package dawflix_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dawflix_api.model.VerificationToken;

public interface TokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
