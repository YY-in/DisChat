package com.yyin.dischat.ui.screen.user_manage

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yyin.dischat.R
import com.yyin.dischat.gateway.event.UserManageEvent
import com.yyin.dischat.ui.component.user_manage.login.*
import com.yyin.dischat.viewmodel.UserManageViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun LoginScreen(
    onClickReturnLanding:() -> Unit,
    onClickForgetPW:() -> Unit,
    onClickLogin:() -> Unit ,
    viewModel: UserManageViewModel = getViewModel()
){
    val state = viewModel.state
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

                LoginButton(onClickButton = onClickLogin)
            }
        }
    }
}

