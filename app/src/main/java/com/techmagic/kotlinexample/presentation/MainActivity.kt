package com.techmagic.kotlinexample.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.techmagic.kotlinexample.R
import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainView {

    private var forecastList: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var presenter: MainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList = find(R.id.rv_forecast_list)
        progressBar = find(R.id.pb_progress)

        presenter.setView(this)

        forecastList!!.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.setView(null)
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

        presenter.loadData()
    }

    override fun showError(message: String?) {
        toast(message as CharSequence)
    }

    override fun showProgress() {
        forecastList!!.visibility = View.GONE
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        forecastList!!.visibility = View.VISIBLE
        progressBar!!.visibility = View.GONE
    }

    override fun showData(weatherData: List<WeatherDataDto>?) {
        forecastList!!.layoutManager = LinearLayoutManager(this)

        if (weatherData != null) {
            forecastList!!.adapter = ForecastListAdapter(weatherData)
        }
    }

    inner class ForecastListAdapter(val items: List<WeatherDataDto>) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            var weatherDataDto : WeatherDataDto = items[position]
            holder!!.temperature!!.text = weatherDataDto.temperature.toString()
            holder.humidity!!.text = weatherDataDto.humidity.toString()
            holder.description!!.text = weatherDataDto.description
            holder.windSpeed!!.text = weatherDataDto.windSpeed.toString()

            Glide.with(this@MainActivity).load(weatherDataDto.iconUrl).into(holder.icon)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_forecastitem, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var temperature: TextView? = null
            var humidity: TextView? = null
            var windSpeed: TextView? = null
            var description: TextView? = null
            var icon: ImageView? = null

            init {
                temperature = view.find(R.id.tv_temperature)
                humidity = view.find(R.id.tv_humidity)
                windSpeed = view.find(R.id.tv_wind_speed)
                description = view.find(R.id.tv_description)
                icon = view.find(R.id.iv_weather_icon)
            }
        }
    }
}