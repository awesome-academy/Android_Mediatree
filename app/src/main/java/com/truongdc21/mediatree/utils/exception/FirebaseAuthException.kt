package com.truongdc21.mediatree.utils.exception

fun String.convertAuthExceptionToString (): String {

    when (this) {
        "ERROR_INVALID_CUSTOM_TOKEN" -> {
            return "The custom token format is incorrect. Please check the documentation."
        }

        "ERROR_CUSTOM_TOKEN_MISMATCH" -> {
            return "The custom token corresponds to a different audience."
        }

        "ERROR_INVALID_CREDENTIAL" -> {
            return "The supplied auth credential is malformed or has expired."
        }

        "ERROR_INVALID_EMAIL" -> {
            return "The email address is badly formatted."
        }

        "ERROR_WRONG_PASSWORD" -> {
            return "The password is invalid or the user does not have a password."
        }

        "ERROR_USER_MISMATCH" -> {
            return "The supplied credentials do not correspond to the previously signed in user."
        }

        "ERROR_REQUIRES_RECENT_LOGIN" -> {
            return "This operation is sensitive and requires recent authentication. Log in again before retrying this request."
        }

        "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" -> {
            return "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."
        }

        "ERROR_EMAIL_ALREADY_IN_USE" -> {
            return "The email address is already in use by another account."
        }

        "ERROR_CREDENTIAL_ALREADY_IN_USE" -> {
            return "This credential is already associated with a different user account."
        }

        "ERROR_USER_DISABLED" -> {
            return "The user account has been disabled by an administrator."
        }

        "ERROR_USER_TOKEN_EXPIRED" -> {
            return "The user\\'s credential is no longer valid. The user must sign in again."
        }

        "ERROR_USER_NOT_FOUND" -> {
            return "There is no user record corresponding to this identifier. The user may have been deleted."
        }

        "ERROR_INVALID_USER_TOKEN" -> {
            return "The user\\'s credential is no longer valid. The user must sign in again."
        }

        "ERROR_OPERATION_NOT_ALLOWED" -> {
            return "This operation is not allowed. You must enable this service in the console."
        }

        "ERROR_WEAK_PASSWORD" -> {
            return "The given password is invalid."
        }
        else -> {
            return ""
        }
    }
}
