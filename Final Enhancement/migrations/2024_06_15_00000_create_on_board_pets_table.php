<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateOnBoardPetsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('on_board_pets', function (Blueprint $table) {
            $table->id()->index();
            $table->string('petName');
            $table->bigInteger('age')->default(0);
            $table->bigInteger('weight')->default(0);
            $table->bigInteger('pricingId')->default(0);
            $table->bigInteger('lockerId')->default(0);
            $table->timestamp('checkIn')->nullable();
            $table->timestamp('checkOut')->nullable();
            $table->timestamp('lastUpdate')->nullable();
            $table->mediumText('description')->nullable();
            $table->mediumText('instructions')->nullable();
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
        Schema::dropIfExists('on_board_pets');
    }
}
