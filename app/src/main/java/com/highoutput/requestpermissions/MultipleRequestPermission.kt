package com.highoutput.requestpermissions

import androidx.compose.runtime.*
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultipleRequestPermission(
    permissions: List<String>,
    deniedMessage: String = "Give this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",
    rationaleMessage: String = "To use this app's functionalities, you need to give us the permission.",
) {
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)

    HandleRequests(
        multiplePermissionsState = multiplePermissionsState,
        deniedContent = { shouldShowRationale ->
            PermissionDeniedContent(
                deniedMessage = deniedMessage,
                rationaleMessage = rationaleMessage,
                shouldShowRationale = shouldShowRationale,
                onRequestPermission = { multiplePermissionsState.launchMultiplePermissionRequest() }
            )
        },
        content = {
            Content(
                text = "PERMISSION GRANTED!",
                showButton = false
            ) {}
        }
    )
}

@ExperimentalPermissionsApi
@Composable
private fun HandleRequests(
    multiplePermissionsState: MultiplePermissionsState,
    deniedContent: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    var shouldShowRationale by remember { mutableStateOf(false) }
    val result = multiplePermissionsState.permissions.all {
        shouldShowRationale = it.status.shouldShowRationale
        it.status == PermissionStatus.Granted
    }
    if (result) {
        content()
    } else {
        deniedContent(shouldShowRationale)
    }
}