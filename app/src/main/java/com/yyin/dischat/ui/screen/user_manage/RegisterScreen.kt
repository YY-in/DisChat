package com.yyin.dischat.ui.screen.user_manage

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.domain.repository.VerifyResult
import com.yyin.dischat.gateway.event.UserManageEvent
import com.yyin.dischat.ui.component.user_manage.login.MyTopBar
import com.yyin.dischat.ui.component.user_manage.register.RegisterTextField
import com.yyin.dischat.ui.component.user_manage.register.SwitchBar
import com.yyin.dischat.ui.theme.Indigo700
import com.yyin.dischat.ui.theme.White
import com.yyin.dischat.viewmodel.UserManageViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel


@Composable
fun RegisterScreen(
    onClickReturnLanding : () -> Unit,
    onConfirm: () -> Unit,
    viewModel : UserManageViewModel = getViewModel(),
) {
    Scaffold(
        topBar = {
                 MyTopBar(
                     text = stringResource(R.string.register),
                     onClickReturn = onClickReturnLanding
                 )
        }
    ) { paddingValues ->
        var isNotError by remember { mutableStateOf(false) }
        var isCodeError by remember { mutableStateOf(false) }
        var isSent by remember { mutableStateOf(false) }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            tonalElevation = 2.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    text = stringResource(R.string.registerTip),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.W900,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .alpha(ContentAlpha.high)
                        .padding(vertical = 15.dp)
                )

                var  placeholder = SwitchBar()

                RegisterTextField(
                    placeholder = placeholder,
                    value =viewModel.state.registerAccount,
                    onValueChange = {
                        viewModel.onEvent(UserManageEvent.RegisterAccountChange(it))
                    },
                    isError =isNotError,
                    onClickCodeButton = {/*TODO*/}
                )
                Spacer(modifier = Modifier.height(20.dp))

                var icon by remember { mutableStateOf(R.drawable.ic_send) }
                //??????isSent???true????????????30s
                if (isSent) {
                    var time = 30
                    LaunchedEffect(key1 = true) {
                        while (time > 0) {
                            delay(1000)
                            time--
                        }
                        isSent = false
                        icon = R.drawable.ic_send
                    }
                }
                //???????????????textField
                TextField(
                    isError=isCodeError,
                    value = viewModel.state.registerCode,
                    onValueChange = {viewModel.onEvent(UserManageEvent.RegisterCodeChange(it))},
                    singleLine = true,
                    placeholder = {
                        Text(stringResource(R.string.verify_code))
                    },
                    trailingIcon = {
                        IconButton(onClick={
                            isNotError = !viewModel.accountState
                            if (!isNotError && !isSent){
                                viewModel.onEvent(UserManageEvent.SendCode)
                                isSent = !isSent
                                icon = R.drawable.ic_replay_30
                            }
                        }){
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                tint = Indigo700
                            )
                        }
                    },
                    shape = RoundedCornerShape(10),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor =  MaterialTheme.colors.surface,
                        textColor = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                        errorIndicatorColor = MaterialTheme.colors.error,
                    ),
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                )
                //???????????????
                Text(
                    text = stringResource(R.string.privacy_policy),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .alpha(ContentAlpha.high)
                        .padding(bottom = 10.dp)
                )

                val context =LocalContext.current
                LaunchedEffect(viewModel, context){
                    viewModel.codeResult.collect{ result->
                        when(result){
                            is VerifyResult.UnKnowError -> {
                                Toast.makeText(context,"Sorry something wrong",Toast.LENGTH_SHORT)
                            }
                            is VerifyResult.UnVerified -> {
                                isCodeError = true
                            }
                            is VerifyResult.Verified -> {
                                onConfirm()
                            }
                            is VerifyResult.WithoutAccount -> {
                                isNotError =true
                            }
                        }
                    }
                }
                Button(
                    onClick = {
                        isCodeError = !viewModel.codeState
                        if (!isCodeError&&!isNotError){
                            viewModel.onEvent(UserManageEvent.VerifyCode)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Indigo700,
                        contentColor = White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(stringResource(R.string.next))
                }
            }
        }
    }

}