package publisher.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class NewsItemDAO extends DataAccessObject
{
	private Logger logger = Logger.getLogger(this.getClass());

	private NewsItem read(ResultSet rs) throws SQLException
	{
		Long id = new Long(rs.getLong("id"));
		String title = rs.getString("title");
		String url = rs.getString("url");
		Date publishDate = rs.getDate("publish_date");
		String description = rs.getString("description");

		NewsItem newsItem = new NewsItem();
		newsItem.setId(id);
		newsItem.setTitle(title);
		newsItem.setUrl(url);
		newsItem.setPublishDate(publishDate);
		newsItem.setDescription(description);

		return newsItem;
	}

	public void delete(NewsItem newsItem)
	{
		PreparedStatement statement = null;
		Connection connection = null;
		try
		{
			connection = getConnection();
			String sql = "delete from news_item where id=?";
			statement = connection.prepareStatement(sql);
			Long id = newsItem.getId();
			statement.setLong(1, id.longValue());
			statement.executeUpdate();
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			close(statement, connection);
		}
	}

	public void update(NewsItem newsItem) throws NotFoundException
	{
		logger.debug("Update news item");

		PreparedStatement statement = null;
		Connection connection = null;
		try
		{
			connection = getConnection();
			String sql = "update news_item set " + "title=?, url=? where id=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, newsItem.getTitle());
			statement.setString(2, newsItem.getUrl());
			statement.setLong(3, newsItem.getId().longValue());

			logger.debug(statement.toString());
			statement.execute();
		} catch (SQLException e)
		{
			logger.debug("Catching SQL Exception");

			// throw new RuntimeException(e);
			throw new NotFoundException(e);
		} finally
		{
			close(statement, connection);
		}

		logger.debug("Returning");
	}

	public void create(NewsItem newsItem)
	{
		Long id = getUniqueId();
		newsItem.setId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try
		{
			connection = getConnection();
			String sql = "insert into news_item " + "(id, title, url) "
					+ "values (?, ?, ?)";
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id.longValue());
			statement.setString(2, newsItem.getTitle());
			statement.setString(3, newsItem.getUrl());
			statement.executeUpdate();
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			close(statement, connection);
		}
	}

	public NewsItem find(Long id)
	{
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;

		try
		{
			connection = getConnection();
			String sql = "select * from news_item where id=?";

			statement = connection.prepareStatement(sql);
			statement.setLong(1, id.longValue());
			rs = statement.executeQuery();

			if (!rs.next())
			{
				return null;
			}
			return read(rs);

		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			close(rs, statement, connection);
		}
	}

	public List<NewsItem> findAll()
	{
		LinkedList<NewsItem> newsItems = new LinkedList<NewsItem>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;

		try
		{
			connection = getConnection();
			String sql = "select * from news_item order by id";
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next())
			{
				NewsItem newsItem = read(rs);
				newsItems.add(newsItem);
			}
			return newsItems;
		} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			close(rs, statement, connection);
		}
	}
}
