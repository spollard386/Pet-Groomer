<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreatePetlockersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('petlockers', function (Blueprint $table) {
            $table->id()->index();
            $table->string('LockerName')->index();
            $table->bigInteger('petId')->default(0);
            $table->string('petName')->nullable();
            $table->timestamp('petInTime')->nullable();
            $table->timestamp('petOutTime')->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('petlockers');
    }
}
