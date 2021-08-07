<template>
    <div >
        <div>&nbsp</div>
       <div id="myChart" :style="{width: '1000px', height: '500px'}"></div>
    </div>
</template>
<script>

    import * as echarts from 'echarts/lib/echarts';
    import 'echarts/lib/chart/bar';
    import 'echarts/lib/component/tooltip';
    import 'echarts/lib/component/title';
    import { LegendComponent } from 'echarts/components';
    import { ToolboxComponent } from 'echarts/components';
    import { GridComponent } from 'echarts/components';
    import { LineChart } from 'echarts/charts';
    echarts.use([LegendComponent]);
    echarts.use([ToolboxComponent]);
    echarts.use([GridComponent]);
    echarts.use([LineChart]);

    import {getRequest} from '../utils/api'

    export default{
        name: "DataCount",
        created() {
          var dateArr = [];
          var dsArr = [];
          getRequest("/dataStatistics").then(resp=> {
            if (resp.status == 200) {
              resp.data.date.map((item)=>{
                this.polar.xAxis.data.push(item);
              })
              resp.data.ds.map((item)=>{
                this.polar.series[0].data.push(item);
              })
            } else {
              this.$message({type: 'error', message: 'Data load fail!'});
            }
          }, resp=> {
            this.$message({type: 'error', message: 'Data load fail!'});
          }).then(resp=>{this.drawLine();});
        },

        methods: {
          drawLine(){
              let myChart = echarts.init(document.getElementById('myChart'));
              myChart.setOption(this.polar, true);
          }
        },

        data() {
          return {
            polar: {
              title: {
                text: ''
              },
              toolbox: {
                show: true,
                feature: {
                  dataZoom: {
                    yAxisIndex: 'none'
                  },
                  dataView: {
                    readOnly: false
                  },
                  magicType: {
                    type: ['line', 'bar']
                  },
                  restore: {},
                  saveAsImage: {}
                }
              },
              tooltip: {trigger: 'axis'},
              legend: {
                data: ['User Register Numbers']
              },
              xAxis: {
                type: 'category',
                boundaryGap: false,
                data: []
              },
              yAxis: {
                type: 'value',
                axisLabel: {
                    formatter: '{value}'
                }
              },
              series: [{
                name: 'User Register Numbers',
                type: 'line',
                data: []
              }],
            }
          }
        }
      }
</script>
