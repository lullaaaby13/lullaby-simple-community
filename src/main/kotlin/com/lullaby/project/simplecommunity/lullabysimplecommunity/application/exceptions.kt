package com.lullaby.project.simplecommunity.lullabysimplecommunity.application

class BadRequestException(message: String): RuntimeException(message)
class UnauthorizedException(message: String): RuntimeException(message)
class ForbiddenException(message: String): RuntimeException(message)
class NotFoundException(message: String): RuntimeException(message)
class ConflictException(message: String): RuntimeException(message)
