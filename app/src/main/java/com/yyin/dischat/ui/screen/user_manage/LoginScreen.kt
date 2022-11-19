package com.yyin.dischat.ui.screen.user_manage

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.domain.repository.AuthResult
import com.yyin.dischat.gateway.event.UserManageEvent
import com.yyin.dischat.ui.component.user_manage.login.*
import com.yyin.dischat.viewmodel.UserManageViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun LoginScreen(
    onClickReturnLanding:() -> Unit,
    onClickForgetPW:() -> Unit,
    onAuthorized: () -> Unit,
    viewModel: UserManageViewModel = getViewModel()
){
    val context = LocalContext.current
    LaunchedEffect(viewModel,context){
        viewModel.authResult.collect{  result ->
            when(result){
                is AuthResult.Authorized -> {
                    onAuthorized()
                }
                is AuthResult.UnAuthorized ->{
                    Toast.makeText(context,"Welcome",Toast.LENGTH_SHORT).apply {
                        setGravity(Gravity.TOP,0,0)
                        show()
                    }
                }
                is AuthResult.UnKnowError ->  {
                    Toast.makeText(context,"Sorry something wrong",Toast.LENGTH_SHORT).apply {
                        setGravity(Gravity.TOP,0,0)
                        show()
                    }
                }
            }
        }
    }

    val state = viewModel.state
    var isAccountError by remember { mutableStateOf(false) }
    var isPaswwordError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MyTopBar(
                text= stringResource(R.string.login),
                onClickReturn = onClickReturnLanding
            )
        }

    ) {paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            tonalElevation = 2.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                LoginText()

                LoginTextField(
                    isAccountError = isAccountError,
                    isPasswordError = isPaswwordError,
                    account = state.loginAccount,
                    password = state.loginPassword,
                    onClickForgetPW = onClickForgetPW,
                    onAccountValueChange = {
                        viewModel.onEvent(UserManageEvent.LoginAccountChange(it))
                    },
                    onPasswordValueChange = {
                        viewModel.onEvent(UserManageEvent.LoginPasswordChange(it))
                    }
                )

                LoginButton(
                    onClickButton = {
                            isPaswwordError = !viewModel.passwordState
                            isAccountError = !viewModel.accountState
                        if(viewModel.accountState && viewModel.passwordState) {
                            viewModel.onEvent(UserManageEvent.Login)
                        }
                    }
                )
            }
        }
    }

}