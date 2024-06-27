<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddmisssingCoumnInPetCar extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('on_board_pets', function (Blueprint $table) {
            $table->string('paymentStatus')->after('lockerId')->default('unpaid');

        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('on_board_pets', function (Blueprint $table) {
            //
        });
    }
}
