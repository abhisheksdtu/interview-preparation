# Back-of-the-Envelope Estimation

In a system design interview, you are often asked to estimate system capacity or performance requirements using a “back-of-the-envelope” approach. Rather than precise figures, you produce order-of-magnitude estimates based on known constants (e.g., powers of two, common latencies, SLAs). The goal is to show structured thinking and reasonable assumptions.

---

## 1. Power of Two

Understanding powers of two is essential when dealing with storage, memory sizes, and data volumes. Below is a quick reference:

| Power | Approximate Value | Full Name    | Short Name |
|:-----:|:-----------------:|:-------------|:----------:|
| 10    | 1 Thousand        | 1 Kilobyte   | 1 KB       |
| 20    | 1 Million         | 1 Megabyte   | 1 MB       |
| 30    | 1 Billion         | 1 Gigabyte   | 1 GB       |
| 40    | 1 Trillion        | 1 Terabyte   | 1 TB       |
| 50    | 1 Quadrillion     | 1 Petabyte   | 1 PB       |

> **Tip**: When estimating storage or bandwidth, round to the nearest power of two (e.g., 1 MB ≈ 10^6 bytes, 1 GB ≈ 10^9 bytes).

---

## 2. Latency Numbers Every Programmer Should Know

Below are typical latencies (circa 2010 and updated values). Use these to gauge where time is spent and to compare the cost of various operations.

| Operation                                  | Time (Approximate)     |
|--------------------------------------------|------------------------|
| L1 cache reference                         | 0.5 ns                 |
| Branch mispredict                          | 5 ns                   |
| L2 cache reference                         | 7 ns                   |
| Mutex lock/unlock                          | 100 ns                 |
| Main memory reference                      | 100 ns                 |
| Compress 1 KB with gzip                    | 10 µs (10,000 ns)      |
| Send 2 KB over 1 Gbps network              | 20 µs (20,000 ns)      |
| Read 1 MB sequentially from memory         | 250 µs (250,000 ns)    |
| Round trip within same datacenter          | 500 µs (500,000 ns)    |
| Disk seek                                  | 10 ms (10,000,000 ns)  |
| Read 1 MB sequentially from network        | 10 ms (10,000,000 ns)  |
| Read 1 MB sequentially from disk           | 20 ms (20,000,000 ns)  |
| Send packet CA→NY→CA (continental US)      | 150 ms (150,000,000 ns)|

> **Notes**:  
> - ns = nanosecond (10⁻⁹ s)  
> - µs = microsecond (10⁻⁶ s)  
> - ms = millisecond (10⁻³ s)  
> - 1 µs = 1,000 ns; 1 ms = 1,000,000 ns.  

### 2.1. Visualizing Latency (Order-of-Magnitude)

- **1 ns** (1 tick)  
- **L1 cache** ≈ 0.5 ns → almost instantaneous.  
- **Branch mispredict** ≈ 5 ns → ~10× L1.  
- **L2 cache** ≈ 7 ns → a bit slower than mispredict.  
- **Mutex lock/unlock** ≈ 100 ns → ~100× L1.  
- **Main memory reference** ≈ 100 ns → same ballpark as lock/unlock.  
- **Compress 1 KB (gzip)** ≈ 10 µs → 10,000 ns (~100× memory).  
- **Send 2 KB over 1 Gbps** ≈ 20 µs → similar to small compression.  
- **Read 1 MB from memory** ≈ 250 µs → ~4× send 2 KB.  
- **Round‐trip datacenter** ≈ 500 µs → ~2× read 1 MB memory.  
- **Disk seek** ≈ 10 ms → 10,000,000 ns (~20× round‐trip).  
- **Read 1 MB from network** ≈ 10 ms → same as disk seek.  
- **Read 1 MB from disk** ≈ 20 ms → ~2× network read.  
- **Cross-continent packet** ≈ 150 ms → ~15× disk read.

> **Implications**:  
> - Memory and cache access are orders of magnitude faster than disk or network.  
> - Batching small I/O operations (e.g., batching reads/writes) is crucial.  
> - Local data processing (in RAM or CPU) is far cheaper than fetching remote resources.

---

## 3. Availability Numbers

Availability (or “uptime”) is often expressed as a percentage over a time period. High availability (HA) means minimal downtime.

| Availability | Downtime per Day | Downtime per Week  | Downtime per Month | Downtime per Year |
|:------------:|:----------------:|:------------------:|:------------------:|:-----------------:|
| 99%          | 14.4 minutes     | 1 hour 40 mins     | 7 hours 18 mins    | 3 days 15 hrs     |
| 99.9%        | 1.44 minutes     | 10 minutes         | 43 minutes         | 8 hours 45 mins   |
| 99.99%       | 8.64 seconds     | 1 minute 0.5 sec   | 4 minutes 19 secs  | 52 minutes 35 sec |
| 99.999%      | 0.864 seconds    | 6 seconds          | 26 seconds         | 5 minutes 15 sec  |

> **Note**: Each additional “9” drastically reduces allowable downtime.  
> - 99% → ~3.65 days/year  
> - 99.9% → ~8.75 hours/year  
> - 99.99% → ~52.6 minutes/year  
> - 99.999% → ~5.26 minutes/year  

---

## 4. Example: Estimate Twitter QPS and Storage Requirements

Below is an illustrative exercise. **Assumptions** are hypothetical and not actual Twitter numbers.

1. **Monthly Active Users (MAU)**: 300 million  
2. **Daily Active Users (DAU)**: 50% of MAU → 150 million  
3. **Tweets per Day per User**: 2 tweets  
4. **Users Who Consume Media**: 10%  
5. **Media data stored per tweet**: 1 MB  
6. **Retention**: 5 years of data  

### 4.1. Query-Per-Second (QPS) Estimation

- **Daily tweets** = DAU × tweets/user = 150 M × 2 = 300 million tweets/day.  
- **Tweets per second (TPS)** = 300 M / (24 hr × 3,600 s)  
  - 24 hr = 86,400 s  
  - TPS ≈ 300 M / 86,400 ≈ 3,472 tweets/sec  
- **Peak QPS**: assume peak is ~2× average  
  - Peak TPS ≈ 7,000 QPS  

### 4.2. Storage Estimation

- **Number of tweets over 5 years** = 300 M/day × 365 × 5 ≈ 547.5 billion tweets  
- **Assume**:  
  - Average text tweet size = 140 bytes → ~0.00014 MB  
  - Media tweets (10%): 547.5 B tweets × 10% = 54.75 B media tweets  
    - Media storage = 54.75 B × 1 MB ≈ 54.75 PB  
  - Text storage = 547.5 B × 0.00014 MB ≈ 76.65 TB  
- **Total storage ≈ 54.83 PB** (round up to ~60 PB to include indices, replicas, metadata)  

> **Note**: Always round up (e.g., ~60 PB). Account for replication factor (× 3 for RAID or distributed FS), caching, and overhead.

---

## 5. Tips for Back-of-the-Envelope Estimation

1. **Outline Your Assumptions**:  
   - Clearly state user counts, rates, data sizes (e.g., “300 M MAU,” “2 tweets/user/day,” etc.).  
   - Interviewers expect to see your thought process and that you know which numbers matter.

2. **Keep Units Consistent**:  
   - Convert everything to base units (e.g., seconds, bytes).  
   - Label your units when writing (e.g., 3,472 QPS = queries/second).

3. **Round to the Nearest “Nice” Number**:  
   - Use powers of two or 10 (e.g., 3,472 ≈ 3,500 QPS).  
   - For storage, use 1 KB, 1 MB, 1 GB, 1 TB, 1 PB.

4. **Use Common Constants**:  
   - **Latency**: RAM ~100 ns; disk seek ~10 ms; network round-trip ~500 µs (in-datacenter).  
   - **Bandwidth**: 1 GBps = 1 B × (10^9 bits/sec) → ~125 MB/s.

5. **Show Order-of-Magnitude Thinking**:  
   - If you estimate 50 PB, interviewer knows it’s ~10^16 bytes. That’s enough to show scale.  
   - Precise digits (e.g., 54.75 PB vs. 60 PB) are less important than correctly identifying “tens of petabytes.”

6. **Be Prepared for Follow-Up Questions**:  
   - E.g., “How would you handle 7,000 QPS at peak?” → talk about load balancers, partitioning, caching.  
   - “Where would you store 60 PB of data?” → discuss distributed file systems (HDFS, S3), compression, tiered storage.

7. **Practice Common Scenarios**:  
   - Social feed QPS & storage, video streaming bandwidth, e-commerce order volume, etc.  
   - The more you practice, the quicker you’ll recall these constants in interviews.

---

## 6. Reference Materials

1. **Dr. Dean from Google** on back-of-the-envelope:  
   - *“Back-of-the-envelope calculations”* by Dr. Dean — outlines heuristics and typical constants.  
2. **Latency Numbers Every Programmer Should Know**:  
   - An article/table listing cache, memory, disk, and network latencies.  
3. **Availability & SLA Guides**:  
   - AWS SLA, Azure SLA, Google Cloud SLA pages for common availability targets.  
4. **System Design Primer**:  
   - “The System Design Primer” GitHub repository (notes on scaling patterns and trade-offs).  

---

_These notes provide a concise summary of “Back-of-the-Envelope Estimation” techniques and reference values. Use them to quickly refresh your mental model of latencies, storage scales, and availability when preparing for system design interviews._  
