package com.highoutput.requestpermissions

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.highoutput.requestpermissions.ui.theme.RequestPermissionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //Single Request
            //SingleRequestPermission(permission = Manifest.permission.READ_CONTACTS)

            //Multiple Request
            MultipleRequestPermission(
                permissions = listOf(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.CAMERA
                )
            )
        }
    }
}
