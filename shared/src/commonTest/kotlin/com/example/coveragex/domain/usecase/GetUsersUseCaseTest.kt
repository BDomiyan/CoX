package com.example.coveragex.domain.usecase

import com.example.coveragex.data.repository.UserRepository
import com.example.coveragex.domain.model.User
import com.example.coveragex.util.NetworkResult
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GetUsersUseCaseTest {

    private lateinit var getUsersUseCase: GetUsersUseCase
    private lateinit var fakeUserRepository: FakeUserRepository

    @Test
    fun `invoke with valid count returns success`() = runTest {
        // Given
        val users = listOf(
            User(
                id = "1",
                fullName = "John Doe",
                email = "john.doe@example.com",
                phone = "123-456-7890",
                pictureLarge = "https://example.com/large.jpg",
                pictureThumb = "https://example.com/thumb.jpg",
                location = "New York, USA"
            )
        )
        fakeUserRepository = FakeUserRepository(users)
        getUsersUseCase = GetUsersUseCase(fakeUserRepository)

        // When
        val result = getUsersUseCase(1)

        // Then
        assertTrue(result is NetworkResult.Success)
        assertEquals(users, (result as NetworkResult.Success).data)
    }

    @Test
    fun `invoke with valid count returns error`() = runTest {
        // Given
        val error = NetworkResult.HttpError(404, "Not Found")
        fakeUserRepository = FakeUserRepository(error)
        getUsersUseCase = GetUsersUseCase(fakeUserRepository)

        // When
        val result = getUsersUseCase(1)

        // Then
        assertTrue(result is NetworkResult.HttpError)
        assertEquals(error, result)
    }

    @Test
    fun `invoke with zero count throws exception`() = runTest {
        // Given
        fakeUserRepository = FakeUserRepository()
        getUsersUseCase = GetUsersUseCase(fakeUserRepository)

        // Then
        assertFailsWith<IllegalArgumentException> {
            getUsersUseCase(0)
        }
    }

    @Test
    fun `invoke with negative count throws exception`() = runTest {
        // Given
        fakeUserRepository = FakeUserRepository()
        getUsersUseCase = GetUsersUseCase(fakeUserRepository)

        // Then
        assertFailsWith<IllegalArgumentException> {
            getUsersUseCase(-1)
        }
    }

    @Test
    fun `invoke with count greater than 100 throws exception`() = runTest {
        // Given
        fakeUserRepository = FakeUserRepository()
        getUsersUseCase = GetUsersUseCase(fakeUserRepository)

        // Then
        assertFailsWith<IllegalArgumentException> {
            getUsersUseCase(101)
        }
    }
}

class FakeUserRepository(
    private val result: NetworkResult<List<User>>
) : UserRepository {

    constructor(users: List<User>) : this(NetworkResult.Success(users))
    constructor() : this(NetworkResult.Success(emptyList()))

    override suspend fun getUsers(count: Int): NetworkResult<List<User>> {
        return result
    }
}
