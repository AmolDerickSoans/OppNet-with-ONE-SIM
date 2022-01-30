# The ONE

The Opportunistic Network Environment simulator.

For introduction and releases, see [the ONE homepage at GitHub](http://akeranen.github.io/the-one/).

For instructions on how to get started, see [the README](https://github.com/akeranen/the-one/wiki/README).

The [wiki page](https://github.com/akeranen/the-one/wiki) has the latest information.

## EpidemicWithScore routing protocol

1. A scoring mechanism for all nodes will be introduced.
2. This scoring mechanism will be based on how often a node comes in contact with another node and a connection is made.
3. Unlike EpidemicRouter where all possible connections are tried for all messages, this protocol will only try to transfer messages to connections where the other node in the        connection has a higher score.
4. The default score for every node will be 100 and for every next encounter it will be incremented by a value of 5.


## Results 

--------------------------------------------------------------------------------------------------
|Epidemic Routing                                |          EpidemicWithScore Routing            |
|------------------------------------------------|------------------------------------------------|
| Message stats for scenario default_scenario    | Message stats for scenario default_scenario    |
|sim_time: 43200.1000                            | sim_time: 43200.1000
|created: 1463                                   | created: 1463
|started: 63918                                  | started: 1380
|relayed: 36236                                  | relayed: 512
|aborted: 27678                                  | aborted: 868
|dropped: 35163                                  | dropped: 856
|removed: 0                                      | removed: 0
|delivered: 585                                  | delivered: 512
|delivery_prob: 0.3999                           | delivery_prob: 0.3500
|response_prob: 0.0000                           | response_prob: 0.0000
|overhead_ratio: 60.9419                         | overhead_ratio: 0.0000
|latency_avg: 5080.8903                          | latency_avg: 6882.7674
|latency_med: 4182.0000                          | latency_med: 6115.2000
|hopcount_avg: 3.8513                            | hopcount_avg: 1.0000
|hopcount_med: 3                                 | hopcount_med: 1
|buffertime_avg: 2146.1209                       | buffertime_avg: 17943.9019
|buffertime_med: 1681.3000                       | buffertime_med: 17970.7000
|rtt_avg: NaN                                    | rtt_avg: NaN
|rtt_med: NaN                                    | rtt_med: NaN          
--------------------------------------------------------------------------------------------------


## Notes

 Epidemic is performing much better than epidemic withScore with regards to delivery probability, even though it is supposed to be the other way around

### Author(s)
- [AmolDerickSoans](https://github.com/AmolDerickSoans)
