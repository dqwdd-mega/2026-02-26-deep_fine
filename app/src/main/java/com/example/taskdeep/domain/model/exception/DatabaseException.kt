package com.example.taskdeep.domain.model.exception

sealed class DatabaseException(
    override val message: String,
    override val cause: Throwable? = null
) : Exception(message, cause) {
    
    class QueryException(
        message: String = "데이터 조회 중 오류가 발생했습니다",
        cause: Throwable?
    ) : DatabaseException(message, cause)
    
    class InsertException(
        message: String = "데이터 저장 중 오류가 발생했습니다",
        cause: Throwable?
    ) : DatabaseException(message, cause)
    
    class UpdateException(
        message: String = "데이터 수정 중 오류가 발생했습니다",
        cause: Throwable?
    ) : DatabaseException(message, cause)
    
    class DeleteException(
        message: String = "데이터 삭제 중 오류가 발생했습니다",
        cause: Throwable?
    ) : DatabaseException(message, cause)
}