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
import com.techmagic.kotlinexample.KotlinExampleApp
import com.techmagic.kotlinexample.R
import com.techmagic.kotlinexample.domain.pojo.WeatherDataDto
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {

    interface OnItemClickListener {
        operator fun invoke(weatherData: WeatherDataDto)
    }

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var forecastList: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        KotlinExampleApp.app?.getAppComponent()?.inject(this)
        presenter.setView(this)
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
        forecastList.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        forecastList.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun showData(weatherData: List<WeatherDataDto>?) {
        forecastList.layoutManager = LinearLayoutManager(this)

        if (weatherData != null) {
            forecastList.adapter = ForecastListAdapter(weatherData) { toast(it.description) }
        }
    }

    private fun initViews() {
        forecastList = find(R.id.rv_forecast_list)
        progressBar = find(R.id.pb_progress)

        forecastList.visibility = View.GONE
    }

    inner class ForecastListAdapter(val items: List<WeatherDataDto>, val itemClick: (WeatherDataDto) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_forecastitem, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(items[position]) {
                holder.temperature!!.text = temperature.toString()
                holder.humidity!!.text = humidity.toString()
                holder.description!!.text = description
                holder.windSpeed!!.text = windSpeed.toString()

                Glide.with(this@MainActivity).load(iconUrl).into(holder.icon!!)
            }

            holder.itemView.setOnClickListener { itemClick(items[position]) }
        }

        override fun getItemCount(): Int = items.size

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