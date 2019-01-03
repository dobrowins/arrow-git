package com.dobrowins.arrowgit

import android.content.Context
import android.widget.Toast

/**
 * @author: Artem Dobrovinsky
 */

fun Context.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()