import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './text-file-content.reducer';

export const TextFileContentDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const textFileContentEntity = useAppSelector(state => state.textFileContent.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="textFileContentDetailsHeading">
          <Translate contentKey="archiscapeCoreApp.textFileContent.detail.title">TextFileContent</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="archiscapeCoreApp.textFileContent.id">Id</Translate>
            </span>
          </dt>
          <dd>{textFileContentEntity.id}</dd>
          <dt>
            <span id="version">
              <Translate contentKey="archiscapeCoreApp.textFileContent.version">Version</Translate>
            </span>
          </dt>
          <dd>{textFileContentEntity.version}</dd>
          <dt>
            <span id="insertDate">
              <Translate contentKey="archiscapeCoreApp.textFileContent.insertDate">Insert Date</Translate>
            </span>
          </dt>
          <dd>
            {textFileContentEntity.insertDate ? (
              <TextFormat value={textFileContentEntity.insertDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="fileName">
              <Translate contentKey="archiscapeCoreApp.textFileContent.fileName">File Name</Translate>
            </span>
          </dt>
          <dd>{textFileContentEntity.fileName}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="archiscapeCoreApp.textFileContent.content">Content</Translate>
            </span>
          </dt>
          <dd>{textFileContentEntity.content}</dd>
        </dl>
        <Button tag={Link} to="/text-file-content" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/text-file-content/${textFileContentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TextFileContentDetail;
