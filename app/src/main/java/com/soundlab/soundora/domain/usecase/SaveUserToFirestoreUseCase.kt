package com.soundlab.soundora.domain.usecase

import com.soundlab.soundora.domain.model.User
import com.soundlab.soundora.domain.repository.UserRepository

class SaveUserToFirestoreUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<Boolean> {
        return userRepository.saveUserToFirestore(user)
    }
}