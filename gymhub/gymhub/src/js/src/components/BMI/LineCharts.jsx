import React from "react";
import {
  CartesianAxis,
  CartesianGrid,
  Line,
  LineChart,
  ResponsiveContainer,
  XAxis,
  YAxis,
} from "recharts";
import { chartData } from "./LineChartData";

const colors = [
  "#15a3ff",
  "#03546c",
  "#946e1f",
  "#f9c55c",
  "#cc0000",
  "#330000",
];

const LineCharts = ({ data = chartData }) => {
  return (
    <div
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        marginTop: 10,
      }}
    >
      <ResponsiveContainer
        width={window.screen.width}
        height={window.screen.height / 3}
      >
        <LineChart
          margin={{
            top: 10,
            right: 30,
            left: 0,
            bottom: 0,
          }}
          data={data}
          width={window.screen.width}
          height={window.screen.height / 3}
        >
          {Object.keys(data[0])
            .slice(1, -1)
            .map((d, i) => (
              <Line
                type={"monotone"}
                dataKey={d}
                stroke={colors[i]}
                strokeWidth={2}
              />
            ))}
          <CartesianAxis stroke="#fff" />
          <CartesianGrid stroke="#f5f5f5" strokeDasharray="3 3" />
          <XAxis dataKey={"date"} fontSize={12} />
          <YAxis dataKey={"Weight"} />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default LineCharts;
