package com.example.ctelassign.repositary

import io.reactivex.Scheduler

interface Schedular {
    fun mainThread(): Scheduler
    fun io(): Scheduler
}