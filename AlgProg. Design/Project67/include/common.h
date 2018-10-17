#ifndef COMMON_H
#define COMMON_H


// enumnerated types
typedef enum month {Jan, Feb, Mar, Apr, May, Jun,
                    Jul, Aug, Sep, Oct, Nov, Dec} Month;

// date structure
typedef struct
{
    int  month;
    int  day;
    int  year;
} Date;

// statistics structure for a list of numbers
typedef struct
{
    float min,
          max,
          mean,
          median,
          stddev_s,
          stddev_p;
} Stats;


// Corporation structues that holds information about a corporation
// and stock pricing data
typedef struct
{
	char name[128],
	     ticker[8],
	     sector[128],
	     industry[128],
	     summary_quote[128];

	float last_sale,
	      market_cap,
	      adr_tso,
          **data; // daily stock prices

    char **date; // dates associated with daily stock pricing

	int	ipo_year,
	    data_rows,
        data_cols,
        date_length;

	Stats *stats;

	Date begin_history,
         end_history;


} Corporation;

#endif
