package com.yyin.dischat.ui.screen.user_manage

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.gateway.event.UserManageEvent
import com.yyin.dischat.ui.component.basic.UriImage
import com.yyin.dischat.ui.component.basic.singleItem
import com.yyin.dischat.ui.component.user_manage.login.MyTopBar
import com.yyin.dischat.ui.theme.Indigo700
import com.yyin.dischat.ui.theme.White
import com.yyin.dischat.ui.widget.PickVisualMediaPersistent
import com.yyin.dischat.viewmodel.UserManageViewModel
import org.koin.androidx.compose.getViewModel



@Composable
fun RegisterDetailScreen(
    onClickReturnLanding : () -> Unit,
    viewModel : UserManageViewModel = getViewModel(),
) {
    val imageSelectLauncher = rememberLauncherForActivityResult(PickVisualMediaPersistent()) {
        viewModel.updateImageUri(it)
    }

    Scaffold(
        topBar = {
            MyTopBar(
                text = stringResource(R.string.register_detail),
                onClickReturn = onClickReturnLanding
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
        ){
            singleItem {
                Box(contentAlignment = Alignment.Center) {
                    Surface(
                        modifier = Modifier.size(96.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 1.dp,
                            SolidColor(MaterialTheme.colorScheme.outline)
                        ),
                        onClick = {
                            //TODO: check permission
                            imageSelectLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                    ) {
                        if (viewModel.imageUri != null) {
                            UriImage(uri = viewModel.imageUri!!)
                        } else {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    modifier = Modifier.size(36.dp),
                                    imageVector = Icons.Rounded.AddAPhoto,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
            singleItem {
                Spacer(Modifier.height(8.dp))
            }
            singleItem {
                OutlinedTextField(
                    value = viewModel.state.registerUsername,
                    onValueChange = {
                            viewModel.onEvent(UserManageEvent.RegisterUsernameChange(it))
                    },
                    label = {
                        Text(stringResource(R.string.select_username))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = null
                        )
                    },
                    singleLine = true,
                    isError = viewModel.errorLabel
                )
            }
            singleItem {
                var shown by rememberSaveable { mutableStateOf(false) }
                val keyboardOptions =
                    remember { KeyboardOptions(keyboardType = KeyboardType.Password) }
                val passwordTransformation = remember { PasswordVisualTransformation() }
                OutlinedTextField(
                    modifier = Modifier,
                    value = viewModel.state.registerPassword,
                    onValueChange = {
                        viewModel.onEvent(UserManageEvent.RegisterPasswordChange(it))
                    },
                    label = {
                        Text(stringResource(R.string.select_password))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Key,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { shown = !shown }) {
                            Icon(
                                imageVector = if (shown) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    },
                    singleLine = true,
                    visualTransformation = if (shown) VisualTransformation.None else passwordTransformation,
                    keyboardOptions = keyboardOptions
                )
            }
            singleItem {
                androidx.compose.material.Button(
                    onClick = {
                        viewModel.onEvent(UserManageEvent.UploadImg)
                        viewModel.onEvent(UserManageEvent.Register)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Indigo700,
                        contentColor = White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    androidx.compose.material.Text(stringResource(R.string.create_account))
                }
            }
        }
    }
}

